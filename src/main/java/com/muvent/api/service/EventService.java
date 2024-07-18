package com.muvent.api.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.EventRequestDTO;
import com.muvent.api.mapper.EventMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventService {

    private final AmazonS3Client s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public Event createEvent(EventRequestDTO eventDTO) {
        String imgUrl = null;

        if (eventDTO.image() != null) {
            imgUrl = this.uploadImg(eventDTO.image());
        }

        Event event = EventMapper.toEvent(eventDTO);
        event.setImgUrl(imgUrl);

        return event;
    }

    private String uploadImg(MultipartFile image) {
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();

        try {
            File file = this.convertMultipartToFile(image);
            s3Client.putObject(bucketName, fileName, file);
            boolean isDeleted = file.delete();

            if (!isDeleted) throw new RuntimeException();

            return s3Client.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        multipartFile.transferTo(convFile);
        return convFile;
    }
}
