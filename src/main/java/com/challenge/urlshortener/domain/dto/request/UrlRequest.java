package com.challenge.urlshortener.domain.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Valid
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlRequest {

    @NotBlank(message = "Original URL cannot be blank")
    @Pattern(regexp = "^(https?://|www\\.)[\\w-]+(\\.[\\w-]+)+(/[^\\s]*)?$", message = "Original URL must be a valid URL")
    @Size(max = 255)
    private String originalUrl;

}
