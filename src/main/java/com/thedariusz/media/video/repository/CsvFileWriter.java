package com.thedariusz.media.video.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileWriter {

    private static final String SEPARATOR = ";";
    private static final String WRAPPER = "\"";
    private static final String NEW_LINE = "\n";

    private final Path path;

    public CsvFileWriter(String path) {
        this.path  = Path.of(path);
    }

    public void createHeader(List<String> fields) {
        String wrappedFields = wrapAndConcatenate(fields);
        writeToFile(wrappedFields);
    }

    public void createLine(List<String> values) {
        String wrappedValues = wrapAndConcatenate(values);
        writeToFile(wrappedValues);
    }

    public void writeToFile(String line) {
        try {
            byte[] bytes = String.join(NEW_LINE, line).concat(NEW_LINE).getBytes();
            Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new CsvFileWriteException(e);
        }
    }

    public String wrapString(String sentence) {
        return sentence == null ? "" : WRAPPER + sentence + WRAPPER;
    }

    public String wrapAndConcatenate(List<String> strings) {
        return strings.stream()
                .map(this::wrapString)
                .collect(Collectors.joining(SEPARATOR));
    }

    private static class CsvFileWriteException extends RuntimeException {
        private static final String MESSAGE = "Failed to writer csv file";

        public CsvFileWriteException(IOException cause) {
            super(MESSAGE, cause);
        }
    }
}
