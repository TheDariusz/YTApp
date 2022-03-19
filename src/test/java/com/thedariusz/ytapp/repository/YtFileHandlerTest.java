package com.thedariusz.ytapp.repository;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

class YtFileHandlerTest {

    @Test
    void shouldWriteListOfYtVideosToFile() throws IOException {
        YtFileHandler ytFileHandler = new YtFileHandler();
        YtVideoModel entity1 = new YtVideoModel(
                "video1",
                OffsetDateTime.of(LocalDate.now(), LocalTime.now(), ZoneOffset.UTC),
                "channel1",
                "Channel title1",
                "some title1",
                "http://localhost/cool.image1.jpg",
                1,
                Duration.of(5, ChronoUnit.MINUTES),
                List.of("tag11", "tag12")
        );
        YtVideoModel entity2 = new YtVideoModel(
                "video2",
                OffsetDateTime.of(LocalDate.now(), LocalTime.now(), ZoneOffset.UTC),
                "channel2",
                "Channel title2",
                "some title2",
                "http://localhost/cool.image2.jpg",
                1,
                Duration.of(5, ChronoUnit.MINUTES),
                List.of("tag21", "tag22")
        );
        List<YtVideoModel> videos = List.of(entity1, entity2);
        ytFileHandler.saveYtVideos(videos);

    }

}