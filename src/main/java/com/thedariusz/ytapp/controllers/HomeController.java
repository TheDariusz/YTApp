package com.thedariusz.ytapp.controllers;

import com.thedariusz.ytapp.model.YtDtoWrapper;
import com.thedariusz.ytapp.network.YtApiService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/home/yt")
    public String displaySecuredPage(Model model) {
        Optional<String> pageToken = Optional.empty();
        YtDtoWrapper ytDtoWrapper = ytApiService.fetchYtVideos();
        model.addAttribute("ytWrapper", ytDtoWrapper);
        return "yt";
    }
}
