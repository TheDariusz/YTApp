package com.thedariusz.ytapp.repository;

import com.thedariusz.ytapp.model.Snippet;
import com.thedariusz.ytapp.model.YtVideoWrapper;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class YtVideoMapper {
    public List<YtVideoModel> toEntity(List<YtVideoWrapper> videos) {
        List<YtVideoModel> ytVideoEntities = new ArrayList<>();

        videos.forEach(video -> {
            Snippet snippet = video.snippet();
            ytVideoEntities.add(
                    new YtVideoModel(
                            video.id(),
                            OffsetDateTime.parse(snippet.publishedAt()),
                            snippet.channelId(),
                            snippet.channelTitle(),
                            snippet.channelTitle(),
                            snippet.thumbnails().defaulted().url(),
                            Integer.parseInt(snippet.categoryId()),
                            Duration.parse(video.contentDetails().duration()),
                            snippet.tags()
                    ));
        });
        return ytVideoEntities;
    }
}
