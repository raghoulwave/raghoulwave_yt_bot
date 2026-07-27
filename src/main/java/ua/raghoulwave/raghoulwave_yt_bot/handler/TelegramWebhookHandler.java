package ua.raghoulwave.raghoulwave_yt_bot.handler;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class TelegramWebhookHandler extends SpringWebhookBot {

    private String botPath;
    private String botUsername;
    private final UpdateHandler updateHandler;

    public TelegramWebhookHandler(
            DefaultBotOptions options,
            SetWebhook setWebhook,
            String botToken,
            UpdateHandler updateHandler
    ) {
        super(options, setWebhook, botToken);
        this.updateHandler = updateHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        log.trace(
                "Received update {}",
                update.getUpdateId()
        );
        return updateHandler.handle(update);
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
