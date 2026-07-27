package ua.raghoulwave.raghoulwave_yt_bot.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ua.raghoulwave.raghoulwave_yt_bot.properties.TelegramProperties;

import java.util.List;

@Configuration
@EnableConfigurationProperties(TelegramProperties.class)
public class TelegramConfig {

    private static final List<String> ALLOWED_UPDATES =
            List.of(
                    "message",
                    "callback_query"
            );

    @Bean
    public DefaultBotOptions options() {

        DefaultBotOptions options = new DefaultBotOptions();
        options.setAllowedUpdates(ALLOWED_UPDATES);

        return options;
    }

    @Bean
    public SetWebhook setWebhook(
            TelegramProperties properties
    ) {

        SetWebhook webhook = new SetWebhook();
        webhook.setUrl(properties.webhookUrl());
        webhook.setAllowedUpdates(ALLOWED_UPDATES);

        return webhook;
    }
}
