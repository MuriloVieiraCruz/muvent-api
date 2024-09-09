package com.muvent.api.service;

import com.muvent.api.domain.address.Address;
import com.muvent.api.domain.address.dto.AddressRequestDTO;
import com.muvent.api.domain.event.Event;
import com.muvent.api.mapper.AddressMapper;
import com.muvent.api.repository.AddressRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public Address createAddress(Event event, @Valid AddressRequestDTO addressRequestDTO) {
        Address address = AddressMapper.toAddress(addressRequestDTO);
        address.setEvent(event);
        return repository.save(address);
    }
}
