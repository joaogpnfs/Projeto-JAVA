package com.codemeet.api.service;

import java.util.UUID;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemeet.api.domain.coupon.Coupon;
import com.codemeet.api.domain.coupon.CouponRequestDTO;
import com.codemeet.api.domain.event.Event;
import com.codemeet.api.repositories.CouponRepository;
import com.codemeet.api.repositories.EventRepository;

@Service
public class CouponService {

  @Autowired
  private CouponRepository couponRepository;

  @Autowired
  private EventRepository eventRepository;

  public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO couponData) {
    Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not found"));

    Coupon coupon = new Coupon();
    coupon.setCode(couponData.code());
    coupon.setDiscount(couponData.discount());
    coupon.setExpirationDate(new Date(couponData.valid()));
    coupon.setEvent(event);

    return couponRepository.save(coupon);
  }

}
