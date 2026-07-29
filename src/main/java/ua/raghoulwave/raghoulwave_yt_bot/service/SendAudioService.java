package ua.raghoulwave.raghoulwave_yt_bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;

@Slf4j
@RequiredArgsConstructor
@Service
public class SendAudioService {

    private final DownloadService downloadService;

    public SendAudio sendAudio(String query) {

        return null;
    }
}
