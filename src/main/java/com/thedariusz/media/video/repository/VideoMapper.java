package com.thedariusz.media.video.repository;

import com.thedariusz.media.video.Video;
import com.thedariusz.media.video.youtube.model.VideoItem;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

import static com.thedariusz.media.video.youtube.model.VideoItem.Snippet;

public class VideoMapper {
    
    public List<Video> toModel(List<VideoItem> videos) {
        return videos.stream()
                .map(this::createVideo)
                .toList();
    }

    private Video createVideo(VideoItem video) {
        Snippet snippet = video.snippet();
        return new Video(
                video.id(),
                OffsetDateTime.parse(snippet.publishedAt()),
                snippet.channelId(),
                snippet.channelTitle(),
                snippet.channelTitle(),
                snippet.thumbnails().defaulted().url(),
                Integer.parseInt(snippet.categoryId()),
                Duration.parse(video.contentDetails().duration()),
                snippet.tags()
        );
    }
}
