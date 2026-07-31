package ua.raghoulwave.raghoulwave_yt_bot.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ua.raghoulwave.raghoulwave_yt_bot.properties.YtDlpProperties;

@Slf4j
@Configuration
@EnableConfigurationProperties(YtDlpProperties.class)
public class YtDlpConfig {
}
