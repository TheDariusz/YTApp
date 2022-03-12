package com.thedariusz.ytapp.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class YtFileHandler implements YtVideoDao {
    @Override
    public void saveYtVideos(List<YtVideoEntity> videos) {
        String fileName = "yt.videos.db.txt";
        Path path = Path.of(fileName);

        videos.forEach(v -> {
            try {
                Files.writeString(path, v.title(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}