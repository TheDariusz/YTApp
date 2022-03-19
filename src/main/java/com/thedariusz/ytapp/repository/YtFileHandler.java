package com.thedariusz.ytapp.repository;

import com.thedariusz.ytapp.utils.FileUtils;
import com.thedariusz.ytapp.utils.FileUtils.FileUtilsException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YtFileHandler implements YtVideoDao {

    private static final String FILE_NAME = "yt.videos.db.csv";

    @Override
    public void saveYtVideos(List<YtVideoModel> videos) {
        List<String> ytVideoModelClassFields = getHeaderFromYtVideoModelClassFields();
        FileDb.setPath(FILE_NAME);

        try {
            String newHeader = wrapAndConcatenate(ytVideoModelClassFields);
            FileDb.writeHeader(newHeader);
            videos.stream()
                    .map(this::convertObjectAttributesToListOfStrings)
                    .map(this::wrapAndConcatenate)
                    .forEach(this::writeLineToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeLineToFile(String line) {
        try {
            FileDb.writeToFile(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String wrapAndConcatenate(List<String> list) {
        try {
            return FileUtils.wrapAndConcatenate(list);
        } catch (FileUtilsException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> convertObjectAttributesToListOfStrings(YtVideoModel v) {
        List<String> list = new ArrayList<>();
        list.add(v.id());
        list.add(v.publishedAt() == null ? "" : v.publishedAt().toString());
        list.add(v.channelId());
        list.add(v.channelTitle());
        list.add(v.title());
        list.add(v.defaultThumbnailUrl());
        list.add(Integer.toString(v.categoryId()));
        list.add(v.duration() == null ? "" : v.duration().toString());
        list.add(v.tags() == null ? "" : v.tags().toString());
        return list;
    }

    private List<String> getHeaderFromYtVideoModelClassFields() {
        Field[] fields = YtVideoModel.class.getDeclaredFields();
        return Arrays.stream(fields)
                .map(Field::getName)
                .toList();
    }
}