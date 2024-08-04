package com.challenge.urlshortener.controller;

import com.challenge.urlshortener.controller.interfaces.IUrlAccessController;
import com.challenge.urlshortener.domain.dto.response.UrlAccessResponse;
import com.challenge.urlshortener.service.interfaces.IUrlAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/access")
@RequiredArgsConstructor
public class UrlAccessController implements IUrlAccessController {

    private final IUrlAccessService urlAccessService;

    @GetMapping("/statistics/{shortUrl}")
    public ResponseEntity<UrlAccessResponse> getAccessStatistics(@PathVariable String shortUrl){
        return ResponseEntity.ok(urlAccessService.getStatistics(shortUrl));
    }
}
