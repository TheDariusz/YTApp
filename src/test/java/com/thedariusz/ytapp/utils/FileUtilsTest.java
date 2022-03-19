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
    void isWrappedWithChar() {
        String sentence = "alamakota kot ma ale";
        String result = FileUtils.wrapString(sentence);
        assertThat(result, is("\"alamakota kot ma ale\""));
    }

    @Test
    void wrapperNullStringShouldReturnAnEmptyString() {
        String sentence = null;
        String result = FileUtils.wrapString(sentence);
        assertThat(result, is(""));
    }

    @Test
    void areConcatenatedWithSemicolonAndQuote() throws FileUtils.FileUtilsException {
        List<String> strings = List.of("Ala ma kota", "Kot ma ale", "Jest im dobrze");
        String result = "\"Ala ma kota\";\"Kot ma ale\";\"Jest im dobrze\"";

        String expected = FileUtils.wrapAndConcatenate(strings);
        System.out.println(expected);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void tryingConcatenateEmptyListThrowsException() {
        List<String> strings = List.of();
        Throwable exception = assertThrows(FileUtils.FileUtilsException.class, () ->
                FileUtils.wrapAndConcatenate(strings));
        Assertions.assertEquals("An empty list!", exception.getMessage());
    }
}