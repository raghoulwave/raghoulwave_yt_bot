package ua.raghoulwave.raghoulwave_yt_bot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.raghoulwave.raghoulwave_yt_bot.entity.Track;
import ua.raghoulwave.raghoulwave_yt_bot.repository.TrackRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TrackService {

    private final TrackRepository repository;

    public Track getOrCreate(Track track) {

        return repository.findByYtId(track.getYtId())
                .map(existing -> {
                    log.info(
                            "Getting existing track {} ({})",
                            existing.getTitle(),
                            existing.getYtId()
                    );
                    return existing;
                })
                .orElseGet(() -> {
                    log.info(
                            "Saving Track {} ({})",
                            track.getTitle(),
                            track.getYtId()
                    );
                    return repository.save(track);
                });
    }

    public Track getByYtId(String ytId) {

        if(repository.existsByYtId(ytId) && repository.findByYtId(ytId).isPresent()) {
            return repository.findByYtId(ytId).get();
        }

        return null;
    }
}
