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
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, updatable = false)
    private String ytId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Version
    private long version;
}
