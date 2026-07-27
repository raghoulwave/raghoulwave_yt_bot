package ua.raghoulwave.raghoulwave_yt_bot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.raghoulwave.raghoulwave_yt_bot.dto.BotUserDto;
import ua.raghoulwave.raghoulwave_yt_bot.entity.BotUser;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy =
                NullValuePropertyMappingStrategy.IGNORE
)
public interface BotUserMapper {

    BotUserDto toDto(BotUser entity);

    BotUser toEntity(BotUserDto dto);

    List<BotUserDto> toDtos(List<BotUser> entities);

    List<BotUser> toEntities(List<BotUserDto> dtos);

    void update(
            BotUserDto dto,
            @MappingTarget BotUser entity
    );
}
