package ua.raghoulwave.raghoulwave_yt_bot.configuration;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.raghoulwave.raghoulwave_yt_bot.properties.YouTubeProperties;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@Configuration
@EnableConfigurationProperties(YouTubeProperties.class)
public class YouTubeConfig {

    @Bean
    public YouTube youtube(YouTubeProperties properties) {
        try {
            return new YouTube.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    _ -> {
                    })
                    .setApplicationName("raghoulwave_yt_bot")
                    .setYouTubeRequestInitializer(
                            new YouTubeRequestInitializer(properties.apiKey())
                    )
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            log.error(
                    "Failed to create YouTube client"
            );
            throw new IllegalStateException(
                    "Failed to create YouTube client",
                    e
            );
        }
    }
}
