package com.muvent.api.domain.address.dto;

public record AddressRequestDTO(

        String neighborhood,
        String city,
        String uf,
        String patio,
        String zipCode,
        String number,
        String complement
) {

}
