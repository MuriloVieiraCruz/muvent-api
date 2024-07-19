package com.muvent.api.service;

import com.muvent.api.domain.address.Address;
import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.EventRequestDTO;
import com.muvent.api.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public Address createAddress(Event event, EventRequestDTO eventRequestDTO) {
        Address address = new Address();
        address.setCity(eventRequestDTO.city());
        address.setUf(eventRequestDTO.uf());
        address.setEvent(event);

        return repository.save(address);
    }
}
