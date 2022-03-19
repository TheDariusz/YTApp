package com.thedariusz.ytapp.repository;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

public record YtVideoModel(
    String id,
    OffsetDateTime publishedAt,
    String channelId,
    String channelTitle,
    String title,
    String defaultThumbnailUrl,
    int categoryId,
    Duration duration,
    List<String> tags
) {}
