package com.thedariusz.media.video;

import com.thedariusz.media.video.repository.VideoDao;
import com.thedariusz.media.video.repository.VideoMapper;
import com.thedariusz.media.video.youtube.model.VideoItem;
import com.thedariusz.media.video.youtube.model.VideoResponse;

import java.util.List;

public class VideoService {

    private final VideoDao fileDb;
    private final VideoMapper mapper;

    public VideoService(VideoDao fileDb) {
        this.fileDb = fileDb;
        this.mapper = new VideoMapper();
    }

    public void save(VideoResponse videoResponse) {
        List<VideoItem> videoItems = videoResponse.items();
        List<Video> videos = mapper.toModel(videoItems);
        fileDb.save(videos);
    }

}
