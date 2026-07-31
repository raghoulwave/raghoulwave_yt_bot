package ua.raghoulwave.raghoulwave_yt_bot.service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.raghoulwave.raghoulwave_yt_bot.entity.Track;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class YouTubeService {

    private final YouTube youtube;
    private final TrackService trackService;

    public List<Track> searchTracks(String query) {
         try {
             YouTube.Search.List search = youtube.search().list("snippet");
             search.setQ(query);
             search.setType("video");
             search.setMaxResults(10L);

             SearchListResponse response = search.execute();
             List<SearchResult> results = response.getItems();

             if (results != null && !results.isEmpty()) {

                 List<Track> tracks =
                         results
                                 .stream()
                                 .map(item -> Track.builder()
                                         .ytId(item.getId().getVideoId())
                                         .title(item.getSnippet().getTitle())
                                         .artist(item.getSnippet().getChannelTitle())
                                         .build())
                                 .toList();

                 tracks.forEach(trackService::getOrCreate);

                 return tracks;
             } else {
                 throw new RuntimeException("No response from YouTube");
             }
         } catch(Exception exception) {
             log.error(
                     "Exception: {}",
                     exception.getMessage()
             );
             return null;
         }
    }
}
