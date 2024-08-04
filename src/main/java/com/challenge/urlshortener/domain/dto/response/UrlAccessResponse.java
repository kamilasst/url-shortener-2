package com.challenge.urlshortener.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlAccessResponse {

    private Integer totalAccesses;
    private Double averageAccessPerDay;

}
