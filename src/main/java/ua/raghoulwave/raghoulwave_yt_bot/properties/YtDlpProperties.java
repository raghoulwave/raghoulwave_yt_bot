package ua.raghoulwave.raghoulwave_yt_bot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;

@ConfigurationProperties(prefix = "raghoulwave.config.ytdlp")
public record YtDlpProperties(
        Path downloadDir
) {
}
