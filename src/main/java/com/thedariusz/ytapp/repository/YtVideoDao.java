package com.thedariusz.ytapp.repository;

import com.thedariusz.ytapp.model.YtVideoWrapper;

import java.io.IOException;
import java.util.List;

public interface YtVideoDao {

    default YtVideoWrapper saveYtVideo(YtVideoWrapper video) {
        return null;
    }

    void saveYtVideos(List<YtVideoModel> videos) throws IOException;

    default YtVideoWrapper readYtVideo() {
        return null;
    }

}
