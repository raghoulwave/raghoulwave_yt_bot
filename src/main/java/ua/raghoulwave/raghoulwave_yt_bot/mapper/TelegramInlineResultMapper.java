package ua.raghoulwave.raghoulwave_yt_bot.mapper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import ua.raghoulwave.raghoulwave_yt_bot.record.TrackSearchResult;

@Component
public class TelegramInlineResultMapper {

    public InlineQueryResult map(
            TrackSearchResult track
    ) {

        InlineQueryResultArticle article = new InlineQueryResultArticle();

        article.setId(track.ytId());
        article.setTitle(track.title());
        article.setDescription(track.artist());

        InputTextMessageContent content = new InputTextMessageContent();

        content.setMessageText(
                "d:" + track.ytId()
        );

        article.setInputMessageContent(content);

        return article;
    }
}
