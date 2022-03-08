package com.thedariusz.ytapp.network;

import com.thedariusz.ytapp.model.UserDtoWrapper;
import com.thedariusz.ytapp.model.YtDtoWrapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class YoutubeWebClient {

    final WebClient webClient;

    public YoutubeWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public YtDtoWrapper fetchYtVideos(String pageToken) {
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
                .bodyToMono(YtDtoWrapper.class)
                .block();
    }


    public UserDtoWrapper fetchUserInfo() {
        String url = "https://www.googleapis.com/oauth2/v3/userinfo";
        return webClient
                .method(HttpMethod.GET)
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserDtoWrapper.class)
                .block();
    }
}




