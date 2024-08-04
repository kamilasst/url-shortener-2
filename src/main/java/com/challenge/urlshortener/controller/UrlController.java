package com.challenge.urlshortener.controller;

import com.challenge.urlshortener.controller.interfaces.IUrlController;
import com.challenge.urlshortener.domain.dto.request.UrlRequest;
import com.challenge.urlshortener.service.interfaces.IUrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlController implements IUrlController {

    private final IUrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> createShortenedUrl(@Valid @RequestBody UrlRequest urlrequest) {
      return ResponseEntity.status(HttpStatus.CREATED).body(urlService.createShortenedUrl(urlrequest));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl){
        return ResponseEntity.ok(urlService.getOriginalUrl(shortUrl));
    }

}
