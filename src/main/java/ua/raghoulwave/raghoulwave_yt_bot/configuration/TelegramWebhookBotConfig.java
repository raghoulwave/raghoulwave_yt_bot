package ua.raghoulwave.raghoulwave_yt_bot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

import java.util.List;

@Configuration
public class TelegramWebhookBotConfig {

    @Value("${raghoulwavebot.config.webhook.url}")
    private String url;
    @Value("${raghoulwavebot.config.webhook.token}")
    private String token;
    @Value("${raghoulwavebot.config.administrator.id}")
    private String administratorId;
    private static final List<String> ALLOWED_UPDATES =
            List.of("message", "callback_query");

    @Bean
    public DefaultBotOptions options() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setAllowedUpdates(ALLOWED_UPDATES);
        return options;
    }

    @Bean
    public SetWebhook setWebhook() {
        SetWebhook webhook = new SetWebhook();
        webhook.setUrl(url);
        webhook.setAllowedUpdates(ALLOWED_UPDATES);
        return webhook;
    }

    @Bean
    public String botToken() {
        return token;
    }

    @Bean
    public String url() {
        return url;
    }

    @Bean
    public String administratorId() {
        return administratorId;
    }
}
