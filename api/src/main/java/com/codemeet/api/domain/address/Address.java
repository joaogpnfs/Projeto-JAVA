package com.codemeet.api.domain.address;

import jakarta.persistence.Table;

import java.util.UUID;

import com.codemeet.api.domain.event.Event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Table(name = "address")
@Entity
public class Address {
  @Id
  @GeneratedValue
  private UUID id;

  private String city;
  private String uf;
  
  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;
}
