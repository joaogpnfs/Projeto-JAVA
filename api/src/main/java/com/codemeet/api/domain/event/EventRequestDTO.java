package com.codemeet.api.domain.event;

import org.springframework.web.multipart.MultipartFile;

public record EventRequestDTO( 
  String title, 
  String description,
  String city, 
  String uf,
  String eventUrl, 
  Long date, 
  Boolean remote,
  MultipartFile image) {

}
