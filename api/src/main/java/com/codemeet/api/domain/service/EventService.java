package com.codemeet.api.domain.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.codemeet.api.domain.event.Event;
import com.codemeet.api.domain.event.EventRequestDTO;

@Service
public class EventService {

  @Value("${aws.bucket.name}")
  private String bucketName;

  
  @Autowired
  private AmazonS3 s3Client;

  public Event createEvent(EventRequestDTO data) {

    String imageUrl = null;

    if(data.image() != null) {
      imageUrl = this.uploadImage(data.image()
    );
    }

    
    Event newEvent = new Event();
    newEvent.setTitle(data.title());
    newEvent.setDescription(data.description());
    newEvent.setEventUrl(data.eventUrl());
    newEvent.setDate(new Date(data.date()));
    newEvent.setRemote(data.remote());
    newEvent.setImageUrl(imageUrl);

    return newEvent;
  }
  

    private String uploadImage(MultipartFile multipartFile) {
      String fileName = UUID.randomUUID().toString() + "-" + multipartFile.getOriginalFilename();
      try {
          File file = this.convertMultipartToFile(multipartFile);
          s3Client.putObject(bucketName, fileName, file);
          file.delete();
          return s3Client.getUrl(bucketName, fileName).toString();
      } catch (Exception e) {
        System.out.println("erro ao subir arquivo");
        return null;
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
