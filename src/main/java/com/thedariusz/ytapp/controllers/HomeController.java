package com.thedariusz.ytapp.controllers;

import com.thedariusz.ytapp.network.YoutubeWebClient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final YoutubeWebClient youtubeWebClient;

    public HomeController(YoutubeWebClient youtubeWebClient) {
        this.youtubeWebClient = youtubeWebClient;
    }

    @GetMapping("/home")
    public String displayHomePage() {
        return "home";
    }

    @GetMapping(value = {"/home/yt"})
    public String displaySecuredPageWithToken(Model model, @AuthenticationPrincipal OidcUser principal, @RequestParam(required = false, name = "page") String pageToken) {
        model.addAttribute("userInfo", youtubeWebClient.fetchUserInfo());
        model.addAttribute("ytWrapper", youtubeWebClient.fetchYtVideos(pageToken));
        return "yt";
    }

}
