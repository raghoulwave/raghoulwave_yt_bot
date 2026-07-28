package ua.raghoulwave.raghoulwave_yt_bot.dispatcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.raghoulwave.raghoulwave_yt_bot.entity.BotUser;
import ua.raghoulwave.raghoulwave_yt_bot.handler.InlineQueryHandler;
import ua.raghoulwave.raghoulwave_yt_bot.handler.MessageHandler;
import ua.raghoulwave.raghoulwave_yt_bot.properties.TelegramProperties;
import ua.raghoulwave.raghoulwave_yt_bot.service.BotUserService;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateDispatcher {

    private final MessageHandler messageHandler;
    private final InlineQueryHandler inlineQueryHandler;

    public BotApiMethod<?> dispatch(Update update) {

        if(update.hasMessage()) {
            return messageHandler.handle(update.getMessage());
        }

        if(update.hasInlineQuery()) {
            return inlineQueryHandler.handle(update.getInlineQuery());
        }

        log.info(
                "Ignoring unsupported Update {}",
                update.getUpdateId()
        );
        return null;
    }
}
