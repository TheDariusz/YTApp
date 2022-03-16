package com.thedariusz.ytapp.utils;

import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    private FileUtils() {}

    public static String wrapWithChar(String sentence, String wrapper) throws FileUtilsException {
        if (wrapper.isBlank() || sentence.isBlank()) {
            throw new FileUtilsException("A string cannot be wrapped! An empty wrapper or a sentence!");
        }
        return wrapper + sentence + wrapper;
    }

    public static String wrapAndConcatenate(List<String> strings, String separator, String wrapper) {
        if (strings.isEmpty() || separator.isEmpty()) {
            return null;
        }

        return strings.stream()
                .map(s -> getWrappedString(wrapper, s))
                .collect(Collectors.joining(separator));

    }

    private static String getWrappedString(String wrapper, String s) {
        try {
            return wrapWithChar(s, wrapper);
        } catch (FileUtilsException e) {
            e.printStackTrace();
        }
        return s;
    }


    public static class FileUtilsException extends Exception {
        public FileUtilsException(String message) {
            super(message);
        }
    }

}
