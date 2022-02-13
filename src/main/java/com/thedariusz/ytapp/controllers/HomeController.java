package com.thedariusz.ytapp.controllers;

import com.thedariusz.ytapp.model.YtDtoWrapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
    public String displaySecuredPage(Model model, @AuthenticationPrincipal OidcUser principal) {

        String url = "https://www.googleapis.com/youtube/v3/channels";

        webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<YtDtoWrapper>() {})
                .block();
//        System.out.println("Principal =" + principal);
//
//        OidcIdToken idToken = principal.getIdToken();
//        String tokenValue = idToken.getTokenValue();
//
//        System.out.println("Token value = " + tokenValue);
        return "yt";
    }
}
