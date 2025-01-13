package com.codemeet.api.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.codemeet.api.domain.event.Event;
import com.codemeet.api.domain.event.EventRequestDTO;
import com.codemeet.api.repositories.AddressRepository;
import com.codemeet.api.domain.address.Address;

@Service
public class AddressService {

  @Autowired
  private AddressRepository addressRepository;

  public void createAddress(EventRequestDTO data, Event event) {
    Address address = new Address();
    address.setCity(data.city());
    address.setUf(data.uf());
    address.setEvent(event);
    addressRepository.save(address);
  }
}
