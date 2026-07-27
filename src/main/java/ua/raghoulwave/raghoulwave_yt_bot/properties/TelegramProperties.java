package ua.raghoulwave.raghoulwave_yt_bot.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "raghoulwave.config.telegram")
public record TelegramProperties(
        @NotBlank
        String webhookUrl,
        @NotBlank
        String webhookSecret,
        @NotBlank
        String botToken,
        @NotNull
        Long administratorId
) {
}
