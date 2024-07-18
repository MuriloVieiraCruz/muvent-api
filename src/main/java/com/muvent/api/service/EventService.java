package com.muvent.api.service;

import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.EventRequestDTO;
import com.muvent.api.domain.event.dto.EventResponseDTO;
import com.muvent.api.mapper.EventMapper;
import com.muvent.api.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    private final EventRepository repository;

    public EventResponseDTO createEvent(EventRequestDTO eventDTO) {
        String imgUrl = null;

        if (eventDTO.image() != null) {
            imgUrl = this.uploadImg(eventDTO.image());
        }

        Event event = EventMapper.toEvent(eventDTO);
        event.setImgUrl(imgUrl);

        return EventMapper.toEventResponse(repository.save(event));
    }

    public Event findEventById(UUID eventId) {
        return repository.findById(eventId).orElseThrow(() -> new RuntimeException(""));
    }

    private String uploadImg(MultipartFile image) {
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();

        try {
            PutObjectRequest putObjRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            s3Client.putObject(putObjRequest, RequestBody.fromByteBuffer(ByteBuffer.wrap(image.getBytes())));

            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            return s3Client.utilities().getUrl(request).toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
