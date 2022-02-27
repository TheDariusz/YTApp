package com.thedariusz.ytapp.network;

import com.thedariusz.ytapp.model.YtDtoWrapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

public class YtApiService {

    final WebClient webClient;
    private String apiKey;

    public YtApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public YtDtoWrapper fetchYtVideos(String pageToken) {
        String url = "https://youtube.googleapis.com/youtube/v3/videos?myRating=like&part=snippet, contentDetails, statistics&maxResults=50";
        url= "%s&pageToken=%s".formatted(url, pageToken);

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




