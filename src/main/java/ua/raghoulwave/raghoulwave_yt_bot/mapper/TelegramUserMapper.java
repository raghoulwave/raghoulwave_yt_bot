package ua.raghoulwave.raghoulwave_yt_bot.mapper;

import org.mapstruct.*;
import org.telegram.telegrambots.meta.api.objects.User;
import ua.raghoulwave.raghoulwave_yt_bot.entity.BotUser;

@Mapper(componentModel = "spring")
public interface TelegramUserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "telegramId", source = "id")
    @Mapping(target = "username", source = "userName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "languageCode", source = "languageCode")
    BotUser toEntity(User telegramUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "telegramId", source = "id")
    @Mapping(target = "username", source = "userName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "languageCode", source = "languageCode")
    void update(User telegramUser,
                      @MappingTarget BotUser entity);
}
