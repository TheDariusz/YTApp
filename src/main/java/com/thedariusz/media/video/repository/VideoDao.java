package com.thedariusz.media.video.repository;

import com.thedariusz.media.video.Video;

import java.util.List;

public interface VideoDao {

    void save(List<Video> videos) ;
    
}
