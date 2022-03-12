package com.thedariusz.ytapp;

import com.thedariusz.ytapp.network.YoutubeWebClient;
import com.thedariusz.ytapp.repository.YtFileHandler;
import com.thedariusz.ytapp.service.YtVideoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class YtAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(YtAppApplication.class, args);
    }

    @Bean
    YoutubeWebClient ytApiService(ClientRegistrationRepository clientRegistrationRepository,
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

        return new YoutubeWebClient(webClient);
    }

    private ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2(ClientRegistrationRepository clientRegistrationRepository,
                                                                       OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, oAuth2AuthorizedClientRepository);

        oauth2.setDefaultOAuth2AuthorizedClient(true);
        return oauth2;
    }

    @Bean
    YtFileHandler ytFileHandler() {
        return new YtFileHandler();
    }

    @Bean
    YtVideoService ytVideoService(YtFileHandler fileHandler) {
        return new YtVideoService(fileHandler);
    }
}
