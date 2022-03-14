package com.thedariusz.ytapp.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileDb {
    private static final FileDb instance = new FileDb();
    private static final String PATH = "yt.videos.db.txt";

    private FileDb() {
    }

    public static FileDb getInstance() {
        return instance;
    }

    public String getPath() {
        return PATH;
    }

    public void writeLines(List<String> stringList) throws IOException {
        byte[] bytes = stringList.stream().collect(Collectors.joining("\n")).getBytes();
        Files.write(Path.of(PATH), bytes);
    }

    public List<String> readLines() throws IOException {
        return Files.readAllLines(Path.of(PATH));
    }

}
