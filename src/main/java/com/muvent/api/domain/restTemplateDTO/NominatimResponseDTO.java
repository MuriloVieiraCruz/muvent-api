package com.muvent.api.domain.restTemplateDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NominatimResponseDTO {

    private String lat;
    private String lon;
}
