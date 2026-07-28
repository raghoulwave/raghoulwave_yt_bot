package ua.raghoulwave.raghoulwave_yt_bot.record;

import lombok.Builder;

@Builder
public record TrackSearchResult(
        String ytId,
        String title,
        String artist
) {
}
