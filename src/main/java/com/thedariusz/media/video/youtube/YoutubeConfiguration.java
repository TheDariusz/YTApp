package com.thedariusz.media.video.youtube;

import com.thedariusz.media.video.VideoService;
import com.thedariusz.media.video.repository.VideoDao;
import com.thedariusz.media.video.repository.VideoFileDatabase;
import com.thedariusz.media.video.repository.CsvFileWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class YoutubeConfiguration {

    @Bean
    VideoClient videoClient(ClientRegistrationRepository clientRegistrationRepository,
                            OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository,
                            @Value("${my.ytapp.apiKey}") String apiKey,
                            @Value("${my.ytapp.baseUrl}") String baseUrl) {

        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .apply(oauth2(clientRegistrationRepository, oAuth2AuthorizedClientRepository).oauth2Configuration())
                .exchangeStrategies(strategies)
                .defaultHeader("key", apiKey)
                .build();

        return new VideoClient(webClient);
    }

    private ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2(ClientRegistrationRepository clientRegistrationRepository,
                                                                       OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, oAuth2AuthorizedClientRepository);

        oauth2.setDefaultOAuth2AuthorizedClient(true);
        return oauth2;
    }

    @Bean
    VideoDao videFileDatabase(CsvFileWriter fileWriter) {
        return new VideoFileDatabase(fileWriter);
    }

    @Bean
    VideoService videoService(VideoDao fileHandler) {
        return new VideoService(fileHandler);
    }
}
