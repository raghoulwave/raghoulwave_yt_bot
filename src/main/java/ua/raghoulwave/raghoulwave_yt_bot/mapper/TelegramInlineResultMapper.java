package ua.raghoulwave.raghoulwave_yt_bot.mapper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import ua.raghoulwave.raghoulwave_yt_bot.entity.Track;

@Component
public class TelegramInlineResultMapper {

    public InlineQueryResult map(
            Track track
    ) {

        InlineQueryResultArticle article = new InlineQueryResultArticle();

        article.setId(track.getYtId());
        article.setTitle(track.getTitle());
        article.setDescription(track.getArtist());

        InputTextMessageContent content = new InputTextMessageContent();

        content.setMessageText(
                "d:" + track.getYtId()
        );

        article.setInputMessageContent(content);

        return article;
    }
}
