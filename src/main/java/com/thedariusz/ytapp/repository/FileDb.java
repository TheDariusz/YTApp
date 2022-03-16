package com.thedariusz.ytapp.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileDb {
    private static Path path;
    private static String header;

    public FileDb() {
    }

    public FileDb(String newPath) {
        setPath(newPath);
    }

    public static void setPath(String newPath) {
        if (path == null && !newPath.isEmpty()) {
            path = Path.of(newPath);
        }
    }

    public static String getPath() {
        return path.toString();
    }

    public static String getHeader() {
        return header;
    }

    public static void writeHeader(String newHeader) throws IOException {
        if (header == null && !newHeader.isEmpty()) {
            header = newHeader;
        }
        writeToFile(newHeader);
    }

    public static void writeToFile(List<String> stringList) throws IOException {
        byte[] bytes = String.join("\n", stringList).getBytes();
        Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static void writeToFile(String line) throws IOException {
        byte[] bytes = String.join("\n", line).getBytes();
        Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public List<String> readFromFile() throws IOException {
        return Files.readAllLines(path);
    }
}
