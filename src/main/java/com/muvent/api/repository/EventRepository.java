package com.muvent.api.repository;

import com.muvent.api.domain.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    Page<Event> findByInitialDateAfter(LocalDateTime date, Pageable pageable);

    @Query("SELECT e FROM Event e "
            + "LEFT JOIN e.address a "
            + "WHERE (:title IS NULL OR e.title LIKE %:title%) "
            + "AND (:city IS NULL OR a.city LIKE %:city%) "
            + "AND (:uf IS NULL OR a.uf LIKE %:uf%) "
            + "AND (e.initialDate >= :startDate AND e.finalDate <= :endDate)")
    Page<Event> findFilteredEvents(
            @Param("title") String title,
            @Param("city") String city,
            @Param("uf") String uf,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}
