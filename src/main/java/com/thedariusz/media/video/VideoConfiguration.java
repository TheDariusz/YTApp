package com.thedariusz.media.video;

import com.thedariusz.media.video.repository.CsvFileWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoConfiguration {

    @Bean
    CsvFileWriter fileWriter(@Value("${video.file.path}") String filePath) {
        return new CsvFileWriter(filePath);
    }
    
}
