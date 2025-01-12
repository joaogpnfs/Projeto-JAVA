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

  @Query("SELECT e FROM Event e WHERE e.date >= :date")
  Page<Event> findUpcomingEvents(@Param("date") Date date, Pageable pageable);
}
