package com.thedariusz.ytapp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class FileUtilsTest {

    @Test
    void isWrappedWithChar() throws FileUtils.FileUtilsException {
        String sentence = "alamakota kot ma ale";
        String sign = "\"";
        String result = FileUtils.wrapWithChar(sentence, sign);
        assertThat(result, is("\"alamakota kot ma ale\""));
    }

    @Test
    void wrapperThrowsException() throws FileUtils.FileUtilsException {
        String sentence = "alamakota kot ma ale";
        String sign = "";
        Throwable exception = assertThrows(FileUtils.FileUtilsException.class, () -> FileUtils.wrapWithChar(sentence, sign));
        Assertions.assertEquals("A string cannot be wrapped! An empty wrapper or a sentence!", exception.getMessage());
    }

    @Test
    void areConcatenatedWithSemicolonAndQuote() throws FileUtils.FileUtilsException {
        List<String> strings = List.of("Ala ma kota", "Kot ma ale", "Jest im dobrze");
        String separator = ";";
        String wrapper = "\"";
        String result = "\"Ala ma kota\";\"Kot ma ale\";\"Jest im dobrze\"";

        String expected = FileUtils.wrapAndConcatenate(strings, separator, wrapper);
        System.out.println(expected);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void tryingConcatenateStringsThrowsException() {
        List<String> strings = List.of("Ala ma kota", "Kot ma ale", "Jest im dobrze");
        String separator = "";
        String wrapper = "\"";
        Throwable exception = assertThrows(FileUtils.FileUtilsException.class, () ->
                FileUtils.wrapAndConcatenate(strings, separator, wrapper));
        Assertions.assertEquals("A separator cannot be empty!", exception.getMessage());
    }
}