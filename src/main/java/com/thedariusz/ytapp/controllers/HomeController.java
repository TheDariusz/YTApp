package com.thedariusz.ytapp.controllers;

import com.thedariusz.ytapp.model.YtDtoWrapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@ConfigurationProperties(prefix = "my.ytapp")
public class HomeController {

    private String apiKey;

    final
    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    final WebClient webClient;

    public HomeController(OAuth2AuthorizedClientService oAuth2AuthorizedClientService, WebClient webClient) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
        this.webClient = webClient;
    }

    @GetMapping("/home")
    public String displayHomePage() {
        return "home";
    }

    @GetMapping("/home/yt")
    public String displaySecuredPage(Model model) {

        String url = "https://youtube.googleapis.com/youtube/v3/videos?myRating=like&part=snippet, contentDetails, statistics";

        YtDtoWrapper ytDtoWrapper = webClient
                .get()
                .uri(url)
                .header("Accept", "application/json")
                .header("key", apiKey)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<YtDtoWrapper>() {
                })
                .block();

        model.addAttribute("ytWrapper", ytDtoWrapper);
        return "yt";
    }
}
