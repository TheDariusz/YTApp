package com.thedariusz.ytapp.repository;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

public record YtVideoEntity(
    String id,
    OffsetDateTime publishedAt,
    String channelId,
    String channelTitle,
    String title,
    String description,
    String defaultThumbnailUrl,
    int categoryId,
    Duration duration,
    List<String> tags
) {}
