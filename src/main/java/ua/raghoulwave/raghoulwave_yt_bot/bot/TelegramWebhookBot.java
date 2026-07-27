package ua.raghoulwave.raghoulwave_yt_bot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import ua.raghoulwave.raghoulwave_yt_bot.dispatcher.UpdateDispatcher;
import ua.raghoulwave.raghoulwave_yt_bot.properties.TelegramProperties;

@Slf4j
@Service
public class TelegramWebhookBot extends SpringWebhookBot {

    private final UpdateDispatcher dispatcher;

    public TelegramWebhookBot(
            DefaultBotOptions options,
            SetWebhook setWebhook,
            TelegramProperties properties,
            UpdateDispatcher dispatcher
    ) {

        super(
                options,
                setWebhook,
                properties.botToken()
        );

        this.dispatcher = dispatcher;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        log.trace(
                "Received update {}",
                update.getUpdateId()
        );
        return dispatcher.dispatch(update);
    }

    @Override
    public String getBotPath() {
        return "";
    }

    @Override
    public String getBotUsername() {
        return "";
    }
}
