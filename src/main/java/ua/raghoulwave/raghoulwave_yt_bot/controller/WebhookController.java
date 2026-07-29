package ua.raghoulwave.raghoulwave_yt_bot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.raghoulwave.raghoulwave_yt_bot.bot.TelegramWebhookBot;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/webhook/${raghoulwave.config.telegram.webhook-secret}")
public class WebhookController {

    private final TelegramWebhookBot telegramWebhookBot;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public PartialBotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramWebhookBot.onUpdateReceived(update);
    }
}
