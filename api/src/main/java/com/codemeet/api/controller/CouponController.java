package com.codemeet.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.codemeet.api.domain.coupon.Coupon;
import com.codemeet.api.domain.coupon.CouponRequestDTO;
import com.codemeet.api.service.CouponService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

  @Autowired
  private CouponService couponService;

  @PostMapping("/event/{eventId}")
  public ResponseEntity<Coupon> addCouponToEvent(@PathVariable UUID eventId, @RequestBody CouponRequestDTO data) {
    Coupon coupon = couponService.addCouponToEvent(eventId, data);
    return ResponseEntity.ok(coupon);
  }
  
}
