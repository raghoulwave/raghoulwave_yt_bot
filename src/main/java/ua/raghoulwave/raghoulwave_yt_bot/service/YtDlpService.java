package ua.raghoulwave.raghoulwave_yt_bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.raghoulwave.raghoulwave_yt_bot.properties.YtDlpProperties;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class YtDlpService {

    private String ytId;
    private final YtDlpProperties properties;

    public String getTrackPath(String ytId) {

        this.ytId = ytId;

        if(downloadTrack()) {
            return properties.downloadDir().toString() + "/track.mp3";
        }

        return null;
    }

    private void clear() {

        File dir = new File(properties.downloadDir().toString());

        if (dir.isDirectory()) {

            Objects.requireNonNull(dir.listFiles());

            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.isFile()) {
                    boolean deleted = file.delete();
                    if (!deleted) {
                        log.error(
                                "Could not delete {}",
                                file.getName()
                        );
                        return;
                    }
                    log.info(
                            "Deleted {}",
                            file.getName()
                    );
                }
            }
        }

    }

    private boolean downloadTrack() {

        clear();

        String command = "yt-dlp";
        String type = "-t";
        String typeName = "mp3";
        String format = "-f";
        String formatType = "bestaudio";
        String pathArg = "-o";
        String path = properties.downloadDir().toString() + "/track";
        String link = "https://music.youtube.com/watch?v=" + ytId;

        try {

            ProcessBuilder processBuilder = new ProcessBuilder(
                    command,
                    type, typeName,
                    format, formatType,
                    pathArg, path,
                    link
            );
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    log.info(
                            "[yt-dlp] {}",
                            line
                    );
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                /* TODO
                 *   make custom exceptions */
                // YtDlpException
                log.error(
                        "{}",
                        processBuilder.command().toString()
                );
                throw new IOException("yt-dlp failed with exit code " + exitCode);
            }

            File[] files = properties.downloadDir().toFile().listFiles(
                    (File _, String name) -> name.endsWith(".mp3")
            );
            if (files == null || files.length == 0) {
                /* TODO
                 *   make custom exceptions */
                // Mp3CreationException
                log.error(
                        "{}",
                        processBuilder.command().toString()
                );
                throw new RuntimeException("No mp3 file was created.");
            }

            File file = files[0];

            for (File f : files) {
                if (f.lastModified() > file.lastModified()) {
                    file = f;
                }
            }

            if (!file.exists()) {
                /* TODO
                 *   make custom exceptions */
                // FileNotFoundException
                log.error(
                        "{}",
                        processBuilder.command().toString()
                );
                throw new RuntimeException("File not found after download.");
            }

            return true;
        } catch (Exception e) {
            log.error(
                    "Exception: {}",
                    e.getMessage()
            );
            throw new RuntimeException(e.getMessage());
        }
    }
}
