package ua.raghoulwave.raghoulwave_yt_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.raghoulwave.raghoulwave_yt_bot.entity.Track;

import java.util.Optional;
import java.util.UUID;

public interface TrackRepository extends JpaRepository<Track, UUID> {

    Optional<Track> findByYtId(String ytId);

    boolean existsByYtId(String ytId);
}
