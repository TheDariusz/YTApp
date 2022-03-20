package com.thedariusz.media.video.youtube.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record VideoItem(
        String kind,
        String etag,
        String id,
        Snippet snippet,
        ContentDetails contentDetails,
        Statistics statistics
) {

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

        public record Thumbnails(
                @JsonProperty("default")
                Thumbnail defaulted,
                Thumbnail medium,
                Thumbnail high,
                Thumbnail standard,
                Thumbnail maxres
        ) {

            public record Thumbnail(
                    String url,
                    int width,
                    int height
            ) {
            }
        }

        public record Localized(
                String title,
                String description
        ) {
        }

    }

    public record ContentDetails(
            String duration,
            String dimension,
            String definition,
            String caption,
            String licensedContent,
            ContentRating contentRating,
            String projection
    ) {

        public record ContentRating() {
        }
    }

    public record Statistics(
            String viewCount,
            String likeCount,
            String favoriteCount,
            String commentCount
    ) {
    }

}
