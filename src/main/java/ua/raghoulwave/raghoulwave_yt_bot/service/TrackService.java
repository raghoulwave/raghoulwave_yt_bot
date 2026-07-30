package ua.raghoulwave.raghoulwave_yt_bot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.raghoulwave.raghoulwave_yt_bot.entity.Track;
import ua.raghoulwave.raghoulwave_yt_bot.mapper.TrackSearchResultMapper;
import ua.raghoulwave.raghoulwave_yt_bot.record.TrackSearchResult;
import ua.raghoulwave.raghoulwave_yt_bot.repository.TrackRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TrackService {

    private final TrackRepository repository;
    private final TrackSearchResultMapper mapper;

    public Track getOrCreate(TrackSearchResult result) {

        return repository.findByYtId(result.ytId())
                .map(existing -> {
                    mapper.update(result, existing);
                    log.info(
                            "Updating existing track {} ({})",
                            existing.getTitle(),
                            existing.getYtId()
                    );
                    return existing;
                })
                .orElseGet(() -> {
                    log.info(
                            "Saving Track {} ({})",
                            result.title(),
                            result.ytId()
                    );
                    return repository.save(mapper.toEntity(result));
                });
    }

    public Track getByYtId(String ytId) {

        if(repository.existsByYtId(ytId) && repository.findByYtId(ytId).isPresent()) {
            return repository.findByYtId(ytId).get();
        }

        return null;
    }
}
