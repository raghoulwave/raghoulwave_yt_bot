package ua.raghoulwave.raghoulwave_yt_bot.mapper;

import org.mapstruct.*;
import ua.raghoulwave.raghoulwave_yt_bot.entity.Track;
import ua.raghoulwave.raghoulwave_yt_bot.record.TrackSearchResult;

@Mapper(componentModel = "spring")
public interface TrackSearchResultMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ytId", source = "ytId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "artist", source = "artist")
    Track toEntity(TrackSearchResult result);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ytId", source = "ytId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "artist", source = "artist")
    void update(TrackSearchResult result,
                @MappingTarget Track entity);
}
