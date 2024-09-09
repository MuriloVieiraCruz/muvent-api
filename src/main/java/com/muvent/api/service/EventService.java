package com.muvent.api.service;

import com.muvent.api.domain.coupon.dto.CouponRequestDTO;
import com.muvent.api.domain.coupon.dto.CouponResponseDTO;
import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.DetailedEventResponseDTO;
import com.muvent.api.domain.event.dto.EventFilterDTO;
import com.muvent.api.domain.event.dto.EventRequestDTO;
import com.muvent.api.domain.event.dto.EventResponseDTO;
import com.muvent.api.exception.EventImageBucketException;
import com.muvent.api.exception.EventNotFoundException;
import com.muvent.api.mapper.CouponMapper;
import com.muvent.api.mapper.EventMapper;
import com.muvent.api.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
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
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.LocalDate;
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
    private final CouponService couponService;

    @Transient
    public EventResponseDTO createEvent(EventRequestDTO eventDTO) {
        String imgUrl = null;

        if (eventDTO.image() != null) {
            imgUrl = eventDTO.image().getOriginalFilename();
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
        return repository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found!"));
    }

    @Cacheable(value = "events", key = "#eventId")
    public DetailedEventResponseDTO getEventDetails(UUID eventId) {
        Event eventFound = this.findEventById(eventId);

        List<CouponResponseDTO> coupons = couponService.consultCoupons(eventId, LocalDate.now());
        DetailedEventResponseDTO eventDTO = EventMapper.toDetailedEventResponse(eventFound);
        eventDTO.couponResponse().addAll(coupons);
        return eventDTO;
    }

    @Cacheable(value = "events")
    public List<EventResponseDTO> getUpcomingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventPage = repository.findByDateAfter(LocalDate.now(), pageable);
        return eventPage.stream()
                .map(EventMapper::toEventResponse)
                .toList();
    }

    @Cacheable(value = "events")
    public List<EventResponseDTO> getFilteredEvents(EventFilterDTO eventFilterDTO) {

        LocalDate startDate =  (eventFilterDTO.startDate() != null) ?
                eventFilterDTO.startDate() : LocalDate.now();

        LocalDate endDate =  (eventFilterDTO.endDate() != null) ?
                eventFilterDTO.endDate() : LocalDate.now().plusYears(5);

        Pageable pageable = PageRequest.of(eventFilterDTO.page(), eventFilterDTO.size());
        Page<Event> eventPage = repository.findFilteredEvents(
                eventFilterDTO.title(), eventFilterDTO.city(), eventFilterDTO.uf(), startDate, endDate, pageable
        );

        return eventPage.stream().parallel()
                .map(EventMapper::toEventResponse)
                .toList();
    }

    public CouponResponseDTO createCouponByEventId(UUID eventId, CouponRequestDTO couponRequestDTO) {
        Event event = this.findEventById(eventId);
        return CouponMapper.toCouponResponse(couponService.createCoupon(event, couponRequestDTO));
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
        } catch (IOException e) {
            throw new EventImageBucketException(e.getMessage());
        }
    }

}
