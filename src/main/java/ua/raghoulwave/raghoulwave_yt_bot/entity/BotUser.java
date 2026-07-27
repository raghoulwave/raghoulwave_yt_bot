package ua.raghoulwave.raghoulwave_yt_bot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ua.raghoulwave.raghoulwave_yt_bot.constant.BotState;

import java.time.Instant;
import java.util.UUID;

// lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// JPA
@Entity
@Table(name = "bot_users")
public class BotUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, updatable = false)
    private Long telegramId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String languageCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BotState state; // individual state for user

    @Builder.Default
    private int page = 0; // user's page counter

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Version
    private long version;


    public void nextPage() {
        page++;
    }

    public void resetPage() {
        page = 0;
    }
}
