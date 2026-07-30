package ua.raghoulwave.raghoulwave_yt_bot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import ua.raghoulwave.raghoulwave_yt_bot.constant.BotState;
import ua.raghoulwave.raghoulwave_yt_bot.entity.BotUser;
import ua.raghoulwave.raghoulwave_yt_bot.mapper.TelegramUserMapper;
import ua.raghoulwave.raghoulwave_yt_bot.repository.BotUserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BotUserService {

    private final BotUserRepository repository;
    private final TelegramUserMapper mapper;

    public BotUser getOrCreate(User telegramUser) {

        return repository.findByTelegramId(telegramUser.getId())
                .map(existing -> {
                    mapper.update(telegramUser, existing);
                    log.info(
                            "Updated existing user {} ({})",
                            existing.getTelegramId(),
                            existing.getUsername()
                    );
                    return existing;
                })
                .orElseGet(() -> {
                    BotUser entity = mapper.toEntity(telegramUser);
                    entity.setState(BotState.START);
                    log.info(
                            "Created user {} ({})",
                            entity.getTelegramId(),
                            entity.getUsername()
                    );
                    return repository.save(entity);
                });
    }
}
