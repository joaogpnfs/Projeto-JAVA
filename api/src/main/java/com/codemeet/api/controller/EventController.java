package com.codemeet.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codemeet.api.domain.event.Event;
import com.codemeet.api.domain.event.EventRequestDTO;
import com.codemeet.api.domain.service.EventService;

@RestController
@RequestMapping("/api/event")
public class EventController {
  @Autowired
  private EventService eventService;

  @PostMapping(consumes = "multipart/form-data")
  public ResponseEntity<Event> create(@RequestParam("title") String title, 
                                      @RequestParam(value = "description", required = false) String description, 
                                      @RequestParam("city") String city, 
                                      @RequestParam("uf") String uf, 
                                      @RequestParam("eventUrl") String eventUrl, 
                                      @RequestParam("date") Long date, 
                                      @RequestParam("remote") Boolean remote, 
                                      @RequestParam(value = "image", required = false) MultipartFile image) {
    EventRequestDTO eventRequestDTO = new EventRequestDTO(title, description, city, uf, eventUrl, date, remote, image);
    Event newEvent = this.eventService.createEvent(eventRequestDTO);
    return ResponseEntity.ok(newEvent);
  }
}
