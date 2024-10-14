package com.muvent.api.service;

import com.muvent.api.domain.address.Address;
import com.muvent.api.domain.address.dto.AddressRequestDTO;
import com.muvent.api.domain.event.Event;
import com.muvent.api.mapper.AddressMapper;
import com.muvent.api.repository.AddressRepository;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class AddressService {

    private final AddressRepository repository;

    public Address createAddress(@Valid AddressRequestDTO addressRequestDTO, Event event) {
        Address address = AddressMapper.toAddress(addressRequestDTO);

        return repository.save(address);
    }
}
