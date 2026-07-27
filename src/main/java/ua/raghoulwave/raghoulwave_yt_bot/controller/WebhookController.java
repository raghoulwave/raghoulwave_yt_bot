package ua.raghoulwave.raghoulwave_yt_bot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.raghoulwave.raghoulwave_yt_bot.handler.TelegramWebhookHandler;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/webhook/${raghoulwavebot.config.webhook.secret}")
public class WebhookController {

    private final TelegramWebhookHandler telegramWebhookHandler;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramWebhookHandler.onWebhookUpdateReceived(update);
    }
}
