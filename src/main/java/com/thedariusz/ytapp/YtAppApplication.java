package com.thedariusz.ytapp;

import com.thedariusz.ytapp.network.YtApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@PropertySource("file:${application_home}/dist/conf/ytapp-config.properties")
public class YtAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(YtAppApplication.class, args);
    }

    @Bean
    WebClient webClient(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, oAuth2AuthorizedClientRepository);

        oauth2.setDefaultOAuth2AuthorizedClient(true);
        return WebClient
                .builder()
                .apply(oauth2.oauth2Configuration())
                .exchangeStrategies(strategies)
                .build();
    }

    @Bean
    YtApiService ytApiService(WebClient webClient) {
        return new YtApiService(webClient);
    }
}
