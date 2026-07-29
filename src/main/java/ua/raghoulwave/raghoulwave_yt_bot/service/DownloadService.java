package ua.raghoulwave.raghoulwave_yt_bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.raghoulwave.raghoulwave_yt_bot.properties.YtDlpProperties;

import java.nio.file.Path;

@Slf4j
@RequiredArgsConstructor
@Service
public class DownloadService {

    private final AudioTagService audioTagService;
    private final YtDlpService ytDlpService;
}
