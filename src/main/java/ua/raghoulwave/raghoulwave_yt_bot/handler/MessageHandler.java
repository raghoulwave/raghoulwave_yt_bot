package ua.raghoulwave.raghoulwave_yt_bot.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.raghoulwave.raghoulwave_yt_bot.bot.TelegramWebhookBot;
import ua.raghoulwave.raghoulwave_yt_bot.entity.BotUser;
import ua.raghoulwave.raghoulwave_yt_bot.service.BotUserService;
import ua.raghoulwave.raghoulwave_yt_bot.service.SendAudioService;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageHandler {

    private final BotUserService botUserService;
    private final SendAudioService sendAudioService;

    public PartialBotApiMethod<?> handle(Message message) {

        BotUser user = botUserService.getOrCreate(message.getFrom());

        if(!message.hasText()) {
            log.error(
                    "Message {} from User {} ({}) has no text",
                    message.getMessageId(),
                    message.getFrom().getId(),
                    message.getFrom().getUserName()
            );
            return SendMessage.builder()
                    .chatId(user.getTelegramId().toString())
                    .text(user.getUsername())
                    .parseMode(ParseMode.HTML)
                    .build();
        }

        if(isDownloadRequest(message.getText())) {
            log.info(
                    "Processing query \"{}\" from user {} ({})",
                    message.getText(),
                    user.getTelegramId(),
                    user.getUsername()
            );
            return sendAudioService.sendAudio(message);
        }

        return SendMessage.builder()
                .chatId(user.getTelegramId().toString())
                .text("Choose a song! :D")
                .parseMode(ParseMode.HTML)
                .build();
    }

    private boolean isDownloadRequest(String text) {
        return text.startsWith("d:");
    }
}
