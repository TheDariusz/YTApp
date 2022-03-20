package com.thedariusz.media.video.api;

import com.thedariusz.media.video.VideoService;
import com.thedariusz.media.video.youtube.VideoClient;
import com.thedariusz.media.video.youtube.model.VideoResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/home")
public class VideoController {

    private final VideoClient videoClient;
    private final VideoService youtubeService;

    public VideoController(VideoClient videoClient, VideoService youtubeService) {
        this.videoClient = videoClient;
        this.youtubeService = youtubeService;
    }

    @GetMapping("/yt")
    public ModelAndView displaySecuredPageWithToken(@AuthenticationPrincipal OidcUser principal,
                                                    @RequestParam(required = false, name = "page") String pageToken) {

        VideoResponse videoResponse = videoClient.fetchVideos(pageToken);
        youtubeService.save(videoResponse);

        Map<String, Object> modelMap = Map.of(
                "userInfo", videoClient.fetchUserInfo(),
                "video", videoResponse,
                "email", principal.getEmail()
        );

        return new ModelAndView("yt", modelMap);
    }

}
