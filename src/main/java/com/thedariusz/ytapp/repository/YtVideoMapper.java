package com.thedariusz.ytapp.repository;

import com.thedariusz.ytapp.model.Snippet;
import com.thedariusz.ytapp.model.YtVideoWrapper;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class YtVideoMapper {
    public List<YtVideoEntity> toEntity(List<YtVideoWrapper> videos) {
        List<YtVideoEntity> ytVideoEntities = new ArrayList<>();

        videos.forEach(video -> {
            Snippet snippet = video.snippet();
            ytVideoEntities.add(
                    new YtVideoEntity(
                            video.id(),
                            OffsetDateTime.parse(snippet.publishedAt()),
                            snippet.channelId(),
                            snippet.channelTitle(),
                            snippet.channelTitle(),
                            snippet.description(),
                            snippet.thumbnails().defaulted().url(),
                            Integer.parseInt(snippet.categoryId()),
                            Duration.parse(video.contentDetails().duration()),
                            snippet.tags()
                    ));
        });
        return ytVideoEntities;
    }
}
