package com.muvent.api.domain.address.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(

        @NotBlank(message = "Neighborhood is required")
        String neighborhood,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String uf,

        @NotBlank(message = "Patio is required")
        String patio,

        @NotBlank(message = "Zipcode is required")
        String zipCode,

        @NotBlank(message = "Neighborhood is required")
        String number,

        String complement
) {

}
