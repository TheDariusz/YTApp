package com.thedariusz.ytapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    final
    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public HomeController(OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @GetMapping("/home")
    public String displayHomePage() {
        return "home";
    }

    @GetMapping("/home/yt")
    public String displaySecuredPage(Model model, @AuthenticationPrincipal OidcUser principal) {


        System.out.println("Principal =" + principal);

        OidcIdToken idToken = principal.getIdToken();
        String tokenValue = idToken.getTokenValue();

        System.out.println("Token value = " + tokenValue);
        return "yt";
    }
}
