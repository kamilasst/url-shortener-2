package com.challenge.urlshortener.service;

import com.challenge.urlshortener.domain.dto.request.UrlRequest;
import org.springframework.stereotype.Service;

@Service
public class UrlShortGenerateServiceImpl {

    public String generate(final UrlRequest urlRequest) {
        int hash = urlRequest.hashCode();
        return Integer.toHexString(hash);
    }

}
