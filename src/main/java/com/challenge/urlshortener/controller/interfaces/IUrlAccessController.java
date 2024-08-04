package com.challenge.urlshortener.controller.interfaces;

import com.challenge.urlshortener.domain.dto.response.UrlAccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IUrlAccessController {

    @Operation(summary = "Get access statistics", description = "Get access statistics via shortened URL - displays average access per day and overall total")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Displays successful access statistics"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error occurred")
            }
    )
    ResponseEntity<UrlAccessResponse> getAccessStatistics(@PathVariable String shortUrl);

}
