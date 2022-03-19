package com.thedariusz.ytapp.utils;

import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    private static final String SEPARATOR = ";";
    private static final String WRAPPER = "\"";

    private FileUtils() {
    }

    public static String wrapString(String sentence) {
        return sentence == null ? "" : WRAPPER + sentence + WRAPPER;
    }

    public static String wrapAndConcatenate(List<String> strings) throws FileUtilsException {
        if (strings.isEmpty()) {
            throw new FileUtilsException("An empty list!");
        }

        return strings.stream()
                .map(FileUtils::wrapString)
                .collect(Collectors.joining(SEPARATOR));

    }

    public static class FileUtilsException extends Exception {
        public FileUtilsException(String message) {
            super(message);
        }
    }

}
