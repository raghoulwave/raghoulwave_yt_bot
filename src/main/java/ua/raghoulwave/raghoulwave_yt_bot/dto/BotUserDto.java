package ua.raghoulwave.raghoulwave_yt_bot.dto;

import lombok.*;
import ua.raghoulwave.raghoulwave_yt_bot.constant.BotState;

import java.util.UUID;

// lombok
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotUserDto {

    private UUID id;

    private Long telegramId;

    private String username;

    private String firstName;

    private String lastName;

    private String languageCode;

    private BotState state;

    private int page;
}
