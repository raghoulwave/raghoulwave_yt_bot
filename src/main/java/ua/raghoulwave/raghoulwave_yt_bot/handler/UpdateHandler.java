package ua.raghoulwave.raghoulwave_yt_bot.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.raghoulwave.raghoulwave_yt_bot.entity.BotUser;
import ua.raghoulwave.raghoulwave_yt_bot.service.BotUserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateHandler {

    private final BotUserService botUserService;

    public SendMessage handle(Update update) {

        if(!update.hasMessage()) {
            log.error(
                    "Update has no message {}",
                    update.getUpdateId()
            );
            return null;
        }

        if(!update.getMessage().hasText()) {
            log.error(
                    "Update has no text {}",
                    update.getUpdateId()
            );
            return null;
        }

        BotUser user = botUserService.getOrCreate(update.getMessage().getFrom());

        log.info(
                "Sending message to user {} ({})",
                user.getTelegramId(),
                user.getUsername()
        );

        return SendMessage.builder()
                .chatId(user.getTelegramId().toString())
                .text("Nyan")
                .parseMode(ParseMode.HTML)
                .build();
    }
}
