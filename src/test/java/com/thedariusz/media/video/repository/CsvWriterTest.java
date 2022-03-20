package com.thedariusz.media.video.repository;

import com.thedariusz.media.video.repository.CsvFileWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CsvWriterTest {
    
    CsvFileWriter writer = new CsvFileWriter("");

    @Test
    void isWrappedWithChar() {

        ArrayList<String> x = new ArrayList<>();
        x.add("adam");
        System.out.println(x);
        
//        String sentence = "alamakota kot ma ale";
//        String result = writer.wrapString(sentence);
//        assertThat(result, is("\"alamakota kot ma ale\""));
    }

    @Test
    void wrapperNullStringShouldReturnAnEmptyString() {
        String sentence = null;
        String result = writer.wrapString(sentence);
        assertThat(result, is(""));
    }

    @Test
    void areConcatenatedWithSemicolonAndQuote() {
        List<String> strings = List.of("Ala ma kota", "Kot ma ale", "Jest im dobrze");
        String result = "\"Ala ma kota\";\"Kot ma ale\";\"Jest im dobrze\"";

        String expected = writer.wrapAndConcatenate(strings);
        System.out.println(expected);
        Assertions.assertEquals(expected, result);
    }

//    @Test
//    void tryingConcatenateEmptyListThrowsException() {
//        List<String> strings = List.of();
//        Throwable exception = assertThrows(.Exception.class, () ->
//                .wrapAndConcatenate(strings));
//        Assertions.assertEquals("An empty list!", exception.getMessage());
//    }
}