package com.thedariusz.ytapp.model;

import java.util.List;

public record YtDtoWrapper(
        String kind,
        String etag,
        List<YtVideo> ytVideoList,
        String nextPageToken,
        PageInfo pageInfo
) {
}
