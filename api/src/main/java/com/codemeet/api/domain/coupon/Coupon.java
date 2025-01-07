package com.codemeet.api.domain.coupon;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

import com.codemeet.api.domain.event.Event;

@Entity
@Table(name = "event_coupon")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Coupon {
  @Id
  @GeneratedValue
  private UUID id;

  private String code;
  private Integer discount;
  private Date expirationDate;

  @ManyToOne
  @JoinColumn(name = "event_id")

  private Event event;
}