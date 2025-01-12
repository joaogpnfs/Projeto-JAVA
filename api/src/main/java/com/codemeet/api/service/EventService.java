package com.codemeet.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.codemeet.api.domain.event.Event;
import com.codemeet.api.domain.event.EventRequestDTO;
import com.codemeet.api.domain.event.EventResponseDTO;
import com.codemeet.api.repositories.EventRepository;

@Service
public class EventService {

  @Value("${aws.bucketName}")
  private String bucketName;

  
  @Autowired
  private AmazonS3 s3Client;

  @Autowired
  private EventRepository eventRepository;

  public Event createEvent(EventRequestDTO data) {

    String imageUrl = null;

    if(data.image() != null) {
      imageUrl = this.uploadImage(data.image());
    }

    Event newEvent = new Event();
    newEvent.setTitle(data.title());
    newEvent.setDescription(data.description());
    newEvent.setEventUrl(data.eventUrl());
    newEvent.setDate(new Date(data.date()));
    newEvent.setRemote(data.remote());
    newEvent.setImageUrl(imageUrl);

    eventRepository.save(newEvent);

    return newEvent;
  }

  public List<EventResponseDTO> getUpcomingEvents(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Event> eventsPage = eventRepository.findUpcomingEvents(new Date(), pageable);

    return eventsPage.map(event -> new EventResponseDTO(
        event.getId(), 
        event.getTitle(), 
        event.getDescription(), 
        event.getDate(), 
        "", 
        "", 
        event.getRemote(), 
        event.getEventUrl(), 
        event.getImageUrl()
    )).stream().toList();

  }

  private String uploadImage(MultipartFile multipartFile) {
    String fileName = UUID.randomUUID().toString() + "-" + multipartFile.getOriginalFilename();
      try {
         File file = this.convertMultipartToFile(multipartFile);            s3Client.putObject(bucketName, fileName, file);
          file.delete();
          return s3Client.getUrl(bucketName, fileName).toString();
      } catch (Exception e) {
          System.out.println("Erro ao fazer upload: " + e.getMessage());
          e.printStackTrace();
          throw new RuntimeException("Falha ao fazer upload da imagem", e);
    }
  }

  private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
    File file = new File(multipartFile.getOriginalFilename());
    try (FileOutputStream fos = new FileOutputStream(file)) {
      fos.write(multipartFile.getBytes());
    }
    return file;
  }
}
