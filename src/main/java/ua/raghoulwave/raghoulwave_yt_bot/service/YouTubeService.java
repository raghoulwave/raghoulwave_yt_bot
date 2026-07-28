package ua.raghoulwave.raghoulwave_yt_bot.service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.raghoulwave.raghoulwave_yt_bot.record.TrackSearchResult;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class YouTubeService {

    private final YouTube youtube;

    public List<TrackSearchResult> searchTracks(String query) {

         try {
             YouTube.Search.List search = youtube.search().list("snippet");
             search.setQ(query);
             search.setType("video");
             search.setMaxResults(10L);

             SearchListResponse response = search.execute();
             List<SearchResult> results = response.getItems();

             if (results != null && !results.isEmpty()) {
                 return results
                         .stream()
                         .map(item -> TrackSearchResult.builder()
                                 .ytId(item.getId().getVideoId())
                                 .title(item.getSnippet().getTitle())
                                 .artist(item.getSnippet().getChannelTitle())
                                 .build())
                         .toList();
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
