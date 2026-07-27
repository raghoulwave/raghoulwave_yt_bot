package ua.raghoulwave.raghoulwave_yt_bot.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ua.raghoulwave.raghoulwave_yt_bot.properties.YtDlpProperties;

@Configuration
@EnableConfigurationProperties(YtDlpProperties.class)
public class YtDlpConfig {
}
