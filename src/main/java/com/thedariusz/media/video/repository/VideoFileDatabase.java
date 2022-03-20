package com.thedariusz.media.video.repository;

import com.thedariusz.media.video.Video;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VideoFileDatabase implements VideoDao {
    
    private final CsvFileWriter csv;

    public VideoFileDatabase(CsvFileWriter csv) {
        this.csv = csv;
    }

    @Override
    public void save(List<Video> videos) {
        List<String> videoFields = getVideoFields();
        
        csv.createHeader(videoFields);
        
        videos.stream()
                .map(this::toValues)
                .forEach(csv::createLine);
    }

    private List<String> toValues(Video video) {
        List<String> list = new ArrayList<>();
        list.add(video.id());
        list.add(video.publishedAt() == null ? "" : video.publishedAt().toString());
        list.add(video.channelId());
        list.add(video.channelTitle());
        list.add(video.title());
        list.add(video.defaultThumbnailUrl());
        list.add(Integer.toString(video.categoryId()));
        list.add(video.duration() == null ? "" : video.duration().toString());
        list.add(video.tags() == null ? "" : video.tags().toString());
        return list;
    }

    private List<String> getVideoFields() {
        Field[] fields = Video.class.getDeclaredFields();
        return Arrays.stream(fields)
                .map(Field::getName)
                .toList();
    }
}