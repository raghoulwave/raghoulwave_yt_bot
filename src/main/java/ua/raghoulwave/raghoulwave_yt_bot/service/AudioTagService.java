package ua.raghoulwave.raghoulwave_yt_bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.springframework.stereotype.Service;
import ua.raghoulwave.raghoulwave_yt_bot.record.TrackSearchResult;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AudioTagService {

    public void setTrackTags(TrackSearchResult result, File file) {

        try {
            AudioFile audioFile = AudioFileIO.read(file);

            Tag tag = audioFile.getTagOrCreateAndSetDefault();

            tag.setField(FieldKey.ARTIST, result.artist());
            tag.setField(FieldKey.TITLE, result.title());

            audioFile.commit();
        } catch(CannotReadException |
                IOException |
                TagException |
                ReadOnlyFileException |
                InvalidAudioFrameException |
                CannotWriteException e
        ) {
            log.error(
                    "JAudioTagger exception: {}",
                    e.getMessage()
            );
        }
    }
}
