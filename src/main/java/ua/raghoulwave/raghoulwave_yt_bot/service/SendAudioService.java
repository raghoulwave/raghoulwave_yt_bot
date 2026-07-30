package ua.raghoulwave.raghoulwave_yt_bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@RequiredArgsConstructor
@Service
public class SendAudioService {

    private final DownloadService downloadService;

    public SendAudio sendAudio(Message message) {

        String ytId = message.getText().replaceFirst("d:", "");

        return SendAudio.builder()
                .chatId(message.getChatId())
                .audio(downloadService.download(ytId))
                .build();
    }
}
