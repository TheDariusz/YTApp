package com.thedariusz.ytapp.controllers;

import com.thedariusz.ytapp.model.YtDtoWrapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class HomeController {

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
    public String displaySecuredPage(Model model, OAuth2AuthenticationToken authentication) {

        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName()
        );

        String url = "https://youtube.googleapis.com/youtube/v3/videos?myRating=like&part=snippet, contentDetails, statistics";

        YtDtoWrapper ytDtoWrapper = webClient
                .get()
                .uri(url)
                .header("Accept","application/json")
                .header("key" ,"AIzaSyAv3ju3wpwt_H-Jumks9EJrtfZEKIjX4fQ")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<YtDtoWrapper>() {
                })
                .block();

        model.addAttribute("ytWrapper", ytDtoWrapper);
        return "yt";
    }
}
