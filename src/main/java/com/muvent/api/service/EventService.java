package com.muvent.api.service;

import com.muvent.api.domain.coupon.dto.CouponRequestDTO;
import com.muvent.api.domain.coupon.dto.CouponResponseDTO;
import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.*;
import com.muvent.api.domain.restTemplateDTO.NominatimResponseDTO;
import com.muvent.api.exception.EventImageBucketException;
import com.muvent.api.exception.EventNotFoundException;
import com.muvent.api.mapper.CouponMapper;
import com.muvent.api.mapper.EventMapper;
import com.muvent.api.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.beans.Transient;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Value("${url.nominatim}")
    private String nominatimUrl;

    private final S3Client s3Client;
    private final EventRepository repository;
    private final CouponService couponService;
    private final RestTemplate restTemplate;

    private static final double MINIMUM_KILOMETER = 20000;

    @Transient
    public EventResponseDTO createEvent(EventRequestDTO eventDTO) {
        String imgUrl = null;

        if (eventDTO.image() != null) {
            imgUrl = eventDTO.image().getOriginalFilename();
        }

        Event event = EventMapper.toEvent(eventDTO);
        event.setImgUrl(imgUrl);

        if (!eventDTO.remote()) {
            event.setLocation(searchLatAndLon(eventDTO));
            event.setAddress(eventDTO.zipCode()
                    + "-" + eventDTO.neighborhood()
                    + "-" + eventDTO.city());
        }

        Event response = repository.save(event);
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
        Page<Event> eventPage = repository.findByInitialDateAfter(LocalDateTime.now(), pageable);
        return eventPage.stream()
                .map(EventMapper::toEventResponse)
                .toList();
    }

    @Cacheable(value = "events")
    public List<EventResponseDTO> getFilteredEvents(EventFilterDTO eventFilterDTO) {

        LocalDateTime startDate =  (eventFilterDTO.startDate() != null) ?
                eventFilterDTO.startDate() : LocalDateTime.now();

        LocalDateTime endDate =  (eventFilterDTO.endDate() != null) ?
                eventFilterDTO.endDate() : LocalDateTime.now().plusYears(5);

        Pageable pageable = PageRequest.of(eventFilterDTO.page(), eventFilterDTO.size());
        Page<Event> eventPage = repository.findFilteredEvents(
                eventFilterDTO.title(), startDate, endDate, pageable
        );

        return eventPage.stream()
                .map(EventMapper::toEventResponse)
                .toList();
    }

    public List<EventResponseDTO> getNearbyEvents(LocationDTO locationDTO) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Point userLocation = geometryFactory.createPoint(new Coordinate(locationDTO.longitude(), locationDTO.latitude()));

        return findEventsNear(userLocation, locationDTO.radius()).stream()
                .map(EventMapper::toEventResponse)
                .toList();
    }

    private List<Event> findEventsNear(Point userLocation, double radius) {
        if (radius <= 0D) {
            radius = MINIMUM_KILOMETER;
        }

        return repository.findEventsWithinRadius(userLocation.getX(), userLocation.getY(), radius);
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

    private Point searchLatAndLon(EventRequestDTO eventDTO) {
        ResponseEntity<NominatimResponseDTO[]> response = restTemplate.getForEntity(formatUrl(nominatimUrl, eventDTO), NominatimResponseDTO[].class);
        List<NominatimResponseDTO> responseList = List.of(Objects.requireNonNull(response.getBody()));
        NominatimResponseDTO nrDTO = responseList.getFirst();
        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createPoint(new Coordinate(Double.parseDouble(nrDTO.getLat()), Double.parseDouble(nrDTO.getLon())));
    }

    private String formatUrl(String url, EventRequestDTO eventDTO) {

        return (url + eventDTO.zipCode()
                + ","
                + eventDTO.neighborhood()
                + ","
                + eventDTO.city()
                + "&format=json");
    }

}
