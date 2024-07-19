package com.muvent.api.service;

import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.EventRequestDTO;
import com.muvent.api.domain.event.dto.EventResponseDTO;
import com.muvent.api.mapper.EventMapper;
import com.muvent.api.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.beans.Transient;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    private final EventRepository repository;

    private final AddressService addressService;

    @Transient
    public EventResponseDTO createEvent(EventRequestDTO eventDTO) {
        String imgUrl = null;

        if (eventDTO.image() != null) {
            imgUrl = this.uploadImg(eventDTO.image());
        }

        Event event = EventMapper.toEvent(eventDTO);
        event.setImgUrl(imgUrl);

        Event response = repository.save(event);

        if (!eventDTO.remote()) {
            event.setAddress(addressService.createAddress(event, eventDTO));
        }

        return EventMapper.toEventResponse(response);
    }

    public Event findEventById(UUID eventId) {
        return repository.findById(eventId).orElseThrow(() -> new RuntimeException(""));
    }

    public List<EventResponseDTO> getUpcomingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventPage = repository.findByDateAfter(LocalDate.now(), pageable);
        return eventPage.stream()
                .map(EventMapper::toEventResponse)
                .toList();
    }

    public List<EventResponseDTO> getFilteredEvents(int page, int size, String title, String city, String uf, LocalDate startDate, LocalDate endDate) {

        startDate =  (startDate != null) ? startDate : LocalDate.now();
        endDate =  (endDate != null) ? endDate : LocalDate.now().plusYears(5);

        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventPage = repository.findFilteredEvents(title, city, uf, startDate, endDate, pageable);
        return eventPage.stream()
                .map(EventMapper::toEventResponse)
                .toList();
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
