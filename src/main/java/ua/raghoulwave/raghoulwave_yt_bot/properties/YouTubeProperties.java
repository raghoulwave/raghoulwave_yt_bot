package ua.raghoulwave.raghoulwave_yt_bot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "raghoulwave.config.google.youtube-data-api-v3")
public record YouTubeProperties(
        String apiKey
) {
}
