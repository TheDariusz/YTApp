package com.thedariusz.ytapp.network;

import com.thedariusz.ytapp.model.YtDtoWrapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class YoutubeWebClient {

    final WebClient webClient;
    private String apiKey;

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
                .bodyToMono(YtDtoWrapper.class)
                .block();
    }
}




