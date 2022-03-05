package com.thedariusz.ytapp.controllers;

import com.thedariusz.ytapp.model.UserDtoWrapper;
import com.thedariusz.ytapp.model.YtDtoWrapper;
import com.thedariusz.ytapp.network.YoutubeWebClient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private static final String EMPTY_PAGE_TOKEN = "";
    private final YoutubeWebClient youtubeWebClient;


    public HomeController(YoutubeWebClient youtubeWebClient) {
        this.youtubeWebClient = youtubeWebClient;
    }

    @GetMapping("/home")
    public String displayHomePage() {
        return "home";
    }

    @GetMapping(value = {"/home/yt/{pageToken}"})
    public String displaySecuredPageWithToken(Model model, @PathVariable String pageToken) {
        YtDtoWrapper ytDtoWrapper = youtubeWebClient.fetchYtVideos(pageToken);
        UserDtoWrapper userDtoWrapper = youtubeWebClient.fetchUserInfo();
        model.addAttribute("userInfo", userDtoWrapper);
        model.addAttribute("ytWrapper", ytDtoWrapper);
        return "yt";
    }

    @GetMapping(value = {"/home/yt"})
    public String displaySecuredPageWithToken(Model model, @AuthenticationPrincipal OidcUser principal) {
        String userEmail = principal.getEmail();
        YtDtoWrapper ytDtoWrapper = youtubeWebClient.fetchYtVideos(EMPTY_PAGE_TOKEN);
        UserDtoWrapper userDtoWrapper = youtubeWebClient.fetchUserInfo();
        model.addAttribute("ytWrapper", ytDtoWrapper);
        model.addAttribute("userInfo", userDtoWrapper);
        return "yt";
    }




}
