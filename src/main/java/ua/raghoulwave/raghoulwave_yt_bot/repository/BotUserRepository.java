package ua.raghoulwave.raghoulwave_yt_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.raghoulwave.raghoulwave_yt_bot.entity.BotUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BotUserRepository extends JpaRepository<BotUser, UUID> {

    Optional<BotUser> findByTelegramId(Long telegramId);

    boolean existsByTelegramId(Long telegramId);
}
