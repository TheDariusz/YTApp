package com.thedariusz.ytapp.controllers;

import com.thedariusz.ytapp.model.YtDtoWrapper;
import com.thedariusz.ytapp.network.YoutubeWebClient;
import com.thedariusz.ytapp.service.YtVideoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final YoutubeWebClient youtubeWebClient;
    private final YtVideoService youtubeService;

    public HomeController(YoutubeWebClient youtubeWebClient, YtVideoService youtubeService) {
        this.youtubeWebClient = youtubeWebClient;
        this.youtubeService = youtubeService;
    }

    @GetMapping("/home")
    public String displayHomePage() {
        return "home";
    }

    @GetMapping(value = {"/home/yt"})
    public String displaySecuredPageWithToken(Model model, @AuthenticationPrincipal OidcUser principal, @RequestParam(required = false, name = "page") String pageToken) {
        model.addAttribute("userInfo", youtubeWebClient.fetchUserInfo());

        YtDtoWrapper ytDtoWrapper = youtubeWebClient.fetchYtVideos(pageToken);
        youtubeService.saveVideosToFile(ytDtoWrapper.items());
        model.addAttribute("ytWrapper", ytDtoWrapper);

        model.addAttribute("email", principal.getEmail());

        return "yt";
    }

}
