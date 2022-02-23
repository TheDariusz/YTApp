package com.thedariusz.ytapp.controllers;

import com.thedariusz.ytapp.model.YtDtoWrapper;
import com.thedariusz.ytapp.network.YtApiService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ConfigurationProperties(prefix = "my.ytapp")
public class HomeController {

    final YtApiService ytApiService;

    public HomeController(YtApiService ytApiService) {
        this.ytApiService = ytApiService;
    }

    @GetMapping("/home")
    public String displayHomePage() {
        return "home";
    }

    @GetMapping("/home/yt")
    public String displaySecuredPage(Model model) {
        YtDtoWrapper ytDtoWrapper = ytApiService.fetchYtVideos();
        model.addAttribute("ytWrapper", ytDtoWrapper);
        return "yt";
    }
}
