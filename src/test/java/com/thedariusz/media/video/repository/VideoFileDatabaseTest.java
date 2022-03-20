package com.thedariusz.media.video.repository;

import com.thedariusz.media.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VideoFileDatabaseTest {

    String VIDEOS_FILE = "videos.csv";

    VideoFileDatabase fileDatabase = new VideoFileDatabase(new CsvFileWriter(VIDEOS_FILE));

    @BeforeEach
    void setup() throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(VIDEOS_FILE));
        writer.write("");
        writer.flush();
    }

    @Test
    void shouldWriteListOfYtVideosToFile() throws IOException {
        //given
        List<Video> videos = List.of(video("video1"), video("video2"));

        //when
        fileDatabase.save(videos);

        //then
        assertThat(Files.readAllLines(Paths.get(VIDEOS_FILE)))
                .isNotEmpty()
                .hasSize(3)
                .containsExactly(
                        "\"id\";\"publishedAt\";\"channelId\";\"channelTitle\";\"title\";\"defaultThumbnailUrl\";\"categoryId\";\"duration\";\"tags\"",
                        "\"video1\";\"2022-03-20T21:12:30.293212Z\";\"channel1\";\"Channel title1\";\"some title1\";\"http://localhost/cool.image1.jpg\";\"1\";\"PT5M\";\"[tag11, tag12]\"",
                        "\"video2\";\"2022-03-20T21:12:30.293212Z\";\"channel1\";\"Channel title1\";\"some title1\";\"http://localhost/cool.image1.jpg\";\"1\";\"PT5M\";\"[tag11, tag12]\""
                );
    }

    private Video video(String name) {
        return new Video(
                name,
                OffsetDateTime.parse("2022-03-20T21:12:30.293212Z"),
                "channel1",
                "Channel title1",
                "some title1",
                "http://localhost/cool.image1.jpg",
                1,
                Duration.of(5, ChronoUnit.MINUTES),
                List.of("tag11", "tag12")
        );
    }

}