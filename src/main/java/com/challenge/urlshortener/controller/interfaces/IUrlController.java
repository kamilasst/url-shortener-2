package com.challenge.urlshortener.controller.interfaces;

import com.challenge.urlshortener.domain.dto.request.UrlRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUrlController {

    @Operation(summary = "Create a shortened url", description = "Create a shortened url via original url")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Shortened url created successfully"),
                    @ApiResponse(responseCode = "400", description = "The request is missing required parameters or contains invalid data."),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error occurred")
            }
    )
    ResponseEntity<String> createShortenedUrl(@Valid @RequestBody UrlRequest urlrequest);

    @Operation(summary = "Get an original url", description = "Get an original url via shortenUrl")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "The original URL was found through the shortened URL"),
                    @ApiResponse(responseCode = "404", description = "Shortened url was not found in the database"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error occurred")
            }
    )
    ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl);

}
