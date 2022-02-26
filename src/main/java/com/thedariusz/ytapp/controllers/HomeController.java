package com.thedariusz.ytapp.controllers;

import com.thedariusz.ytapp.model.YtDtoWrapper;
import com.thedariusz.ytapp.network.YtApiService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeController {

    private final YtApiService ytApiService;

    public HomeController(YtApiService ytApiService) {
        this.ytApiService = ytApiService;
    }

    @GetMapping("/home")
    public String displayHomePage() {
        return "home";
    }

    @GetMapping(value = {"/home/yt/{pageToken}"})
    public String displaySecuredPageWithToken(Model model, @PathVariable String pageToken) {
        YtDtoWrapper ytDtoWrapper = ytApiService.fetchYtVideos(pageToken);
        model.addAttribute("ytWrapper", ytDtoWrapper);
        return "yt";
    }

    @GetMapping(value = {"/home/yt"})
    public String displaySecuredPageWithToken(Model model) {
        YtDtoWrapper ytDtoWrapper = ytApiService.fetchYtVideos(null);
        model.addAttribute("ytWrapper", ytDtoWrapper);
        return "yt";
    }


}
