package com.codemeet.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codemeet.api.domain.event.Event;
import com.codemeet.api.domain.event.EventRequestDTO;
import com.codemeet.api.domain.service.EventService;

@RestController
@RequestMapping("/api/event")
public class EventController {
  @Autowired
  private EventService eventService;

  @PostMapping
  public ResponseEntity<Event> create(@RequestBody EventRequestDTO body) {
    Event newEvent = this.eventService.createEvent(body);
    return ResponseEntity.ok(newEvent);
  }

}
