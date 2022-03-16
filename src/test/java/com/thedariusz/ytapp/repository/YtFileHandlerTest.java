package com.thedariusz.ytapp.repository;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YtFileHandlerTest {

    @Test
    void shouldReturnListOfStringsFromClass() throws IOException {
        YtFileHandler ytFileHandler = new YtFileHandler();
        ytFileHandler.saveYtVideos(null);

    }

}