package com.thedariusz.ytapp.repository;

import com.thedariusz.ytapp.utils.FileUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class YtFileHandler implements YtVideoDao {
    @Override
    public void saveYtVideos(List<YtVideoModel> videos) throws IOException {
        String fileName = "yt.videos.db.txt";
        List<String> fromClassFields = getHeaderFromClassFields();
        FileDb.setPath(fileName);

        String newHeader = FileUtils.wrapAndConcatenate(fromClassFields, ";", "\"");
        FileDb.writeHeader(newHeader);

    }

    private List<String> getHeaderFromClassFields() {
        Field[] fields = YtVideoModel.class.getDeclaredFields();
        return Arrays.stream(fields)
                .map(Field::getName)
                .toList();
    }
}