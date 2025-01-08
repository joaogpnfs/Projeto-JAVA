package com.codemeet.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codemeet.api.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, UUID>{

}
