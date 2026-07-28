package ua.raghoulwave.raghoulwave_yt_bot.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import ua.raghoulwave.raghoulwave_yt_bot.mapper.TelegramInlineResultMapper;
import ua.raghoulwave.raghoulwave_yt_bot.record.TrackSearchResult;
import ua.raghoulwave.raghoulwave_yt_bot.service.YouTubeService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class InlineQueryHandler {

    private final YouTubeService youTubeService;
    private final TelegramInlineResultMapper mapper;

    public AnswerInlineQuery handle(InlineQuery query) {

        if(query.getQuery().isEmpty()) {
            return null;
        }

        log.info(
                "Searching {}",
                query.getQuery()
        );

        List<TrackSearchResult> tracks = youTubeService.searchTracks(query.getQuery());

        List<InlineQueryResult> results =
                tracks.stream()
                        .map(mapper::map)
                        .toList();

        return AnswerInlineQuery.builder()
                .inlineQueryId(query.getId())
                .results(results)
                .cacheTime(0)
                .isPersonal(true)
                .build();
    }
}
