package com.thedariusz.media.video.youtube;

import com.thedariusz.media.video.youtube.model.UserResponse;
import com.thedariusz.media.video.youtube.model.VideoResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class VideoClient {

    final WebClient webClient;

    public VideoClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public VideoResponse fetchVideos(String pageToken) {
        return webClient
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/videos")
                        .queryParam("myRating", "like")
                        .queryParam("part", "snippet, contentDetails, statistics")
                        .queryParam("maxResults", 50)
                        .queryParam("pageToken", pageToken)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("BAD REQUEST!")))
                .bodyToMono(VideoResponse.class)
                .block();
    }


    public UserResponse fetchUserInfo() {
        String url = "https://www.googleapis.com/oauth2/v3/userinfo";
        return webClient
                .method(HttpMethod.GET)
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
    }
}




