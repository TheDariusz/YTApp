package com.thedariusz.media.video.youtube.model;

import java.util.List;

public record VideoResponse(
        String kind,
        String etag,
        List<VideoItem> items,
        String nextPageToken,
        String prevPageToken,
        PageInfo pageInfo
) {

    public record PageInfo(
            int totalResults,
            int resultsPerPage
    ) {
    }
}
