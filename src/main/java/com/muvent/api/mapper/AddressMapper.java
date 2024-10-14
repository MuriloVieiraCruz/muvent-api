package com.muvent.api.mapper;

import com.muvent.api.domain.address.Address;
import com.muvent.api.domain.address.dto.AddressRequestDTO;
import com.muvent.api.domain.address.dto.AddressResponseDTO;
import com.muvent.api.domain.event.Event;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public static Address toAddress(AddressRequestDTO addressRequestDTO) {
        return Address.builder()
                .neighborhood(addressRequestDTO.neighborhood())
                .city(addressRequestDTO.city())
                .uf(addressRequestDTO.uf())
                .patio(addressRequestDTO.patio())
                .zipCode(addressRequestDTO.zipCode())
                .number(addressRequestDTO.number())
                .complement(addressRequestDTO.complement())
                .build();
    }

    public static AddressResponseDTO toAddressResponse(Address address) {
        return new AddressResponseDTO(
                address.getNeighborhood(),
                address.getCity(),
                address.getUf(),
                address.getPatio(),
                address.getZipCode(),
                address.getNumber(),
                address.getComplement()
        );
    }
}
