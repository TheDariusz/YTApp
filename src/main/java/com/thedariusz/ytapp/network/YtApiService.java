package com.thedariusz.ytapp.network;

import com.thedariusz.ytapp.model.YtDtoWrapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

public class YtApiService {

    final WebClient webClient;
    private String apiKey;

    public YtApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public YtDtoWrapper fetchYtVideos() {
        String url = "https://youtube.googleapis.com/youtube/v3/videos?myRating=like&part=snippet, contentDetails, statistics&maxResults=10";

        return webClient
                .get()
                .uri(url)
                .header("Accept", "application/json")
                .header("key", apiKey)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<YtDtoWrapper>() {
                })
                .block();
    }
}




