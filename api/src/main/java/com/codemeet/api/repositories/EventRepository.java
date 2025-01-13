package com.codemeet.api.repositories;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codemeet.api.domain.event.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

  @Query("SELECT e FROM Event e LEFT JOIN e.address a WHERE e.date >= :date")
  public Page<Event> findUpcomingEvents(@Param("date") Date date, Pageable pageable);

  @Query("SELECT e FROM Event e LEFT JOIN e.address a " 
        + "WHERE (:title IS NULL OR e.title LIKE %:title%) "
        + "AND (:city IS NULL OR a.city LIKE %:city%) "
        + "AND (:uf IS NULL OR a.uf LIKE %:uf%) " 
        + "AND (e.date >= :startDate AND e.date <= :endDate)")
  Page<Event> findFilteredEvents(@Param("title") String title,
    @Param("city") String city,
    @Param("uf") String uf,
    @Param("startDate") Date startDate,
    @Param("endDate") Date endDate,
    Pageable pageable);
}
