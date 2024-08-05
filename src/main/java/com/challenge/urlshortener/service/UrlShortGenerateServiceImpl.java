package com.challenge.urlshortener.service;

import com.challenge.urlshortener.domain.dto.request.UrlRequest;
import com.challenge.urlshortener.service.interfaces.IUrlShortGenerateService;
import org.springframework.stereotype.Service;

@Service
public class UrlShortGenerateServiceImpl implements IUrlShortGenerateService {

    @Override
    public String generate(final UrlRequest urlRequest) {
        int hash = urlRequest.hashCode();
        return Integer.toHexString(hash);
    }

}
