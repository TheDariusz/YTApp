package com.thedariusz.ytapp.service;

import com.thedariusz.ytapp.model.YtVideoWrapper;
import com.thedariusz.ytapp.repository.YtFileHandler;
import com.thedariusz.ytapp.repository.YtVideoModel;
import com.thedariusz.ytapp.repository.YtVideoMapper;

import java.util.List;

public class YtVideoService {

    private final YtFileHandler fileHandler;

    public YtVideoService(YtFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void saveVideosToFile(List<YtVideoWrapper> videos) {
        YtVideoMapper mapper = new YtVideoMapper();
        List<YtVideoModel> videoEntities = mapper.toEntity(videos);
        fileHandler.saveYtVideos(videoEntities);
    }

}
