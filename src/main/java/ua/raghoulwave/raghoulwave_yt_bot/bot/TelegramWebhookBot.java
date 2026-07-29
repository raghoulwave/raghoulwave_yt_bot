package ua.raghoulwave.raghoulwave_yt_bot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import ua.raghoulwave.raghoulwave_yt_bot.dispatcher.UpdateDispatcher;
import ua.raghoulwave.raghoulwave_yt_bot.properties.TelegramProperties;

@Slf4j
@Service
public class TelegramWebhookBot extends SpringWebhookBot {

    private final UpdateDispatcher dispatcher;
    private final TelegramProperties telegramProperties;

    public TelegramWebhookBot(
            DefaultBotOptions options,
            SetWebhook setWebhook,
            TelegramProperties properties,
            UpdateDispatcher dispatcher,
            TelegramProperties telegramProperties) {

        super(
                options,
                setWebhook,
                properties.botToken()
        );

        this.dispatcher = dispatcher;
        this.telegramProperties = telegramProperties;
    }

    public PartialBotApiMethod<?> onUpdateReceived(Update update) {
        log.info(
                "Received update {}",
                update.getUpdateId()
        );
        PartialBotApiMethod<?> method = dispatcher.dispatch(update);
        if(method instanceof SendAudio) {
            try {
                execute((SendAudio) method);
            } catch(TelegramApiException e) {
                log.info(
                        "Exception: {}",
                        e.getMessage()
                );
            }
        }
        return method;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return telegramProperties.webhookUrl();
    }

    @Override
    public String getBotUsername() {
        return "raghoulwave_bot";
    }
}
