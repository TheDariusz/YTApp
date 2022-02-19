package com.thedariusz.ytapp.model;

import java.util.List;

public record Snippet(
        String publishedAt,
        String channelId,
        String title,
        String description,
        Thumbnails thumbnails,
        String channelTitle,
        List<String> tags,
        String categoryId,
        String liveBroadcastContent,
        Localized localized,
        String defaultAudioLanguage
) {
}
