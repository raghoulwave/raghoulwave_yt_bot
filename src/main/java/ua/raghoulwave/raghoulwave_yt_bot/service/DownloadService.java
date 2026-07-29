package ua.raghoulwave.raghoulwave_yt_bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ua.raghoulwave.raghoulwave_yt_bot.properties.YtDlpProperties;

import java.io.File;
import java.nio.file.Path;

@Slf4j
@RequiredArgsConstructor
@Service
public class DownloadService {

    private final AudioTagService audioTagService;
    private final YtDlpService ytDlpService;

    public InputFile download(String ytId) {
        // Audio Tag
        return new InputFile(new File(ytDlpService.getTrackPath(ytId)));
    }
}
