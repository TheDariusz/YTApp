package com.thedariusz.ytapp.model;

public record YtVideo(
        String kind,
        String etag,
        String id,
        Snippet snippet,
        ContentDetails contentDetails,
        Statistics statistics
) {
}
