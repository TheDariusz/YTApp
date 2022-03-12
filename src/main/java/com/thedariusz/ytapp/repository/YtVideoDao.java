package com.thedariusz.ytapp.repository;

import com.thedariusz.ytapp.model.YtVideoWrapper;

import java.util.List;

public interface YtVideoDao {

    default YtVideoWrapper saveYtVideo(YtVideoWrapper video) {
        return null;
    }

    void saveYtVideos(List<YtVideoEntity> videos);

    default YtVideoWrapper readYtVideo() {
        return null;
    }

}
