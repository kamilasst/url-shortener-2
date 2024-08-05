package com.challenge.urlshortener.service.interfaces;

import com.challenge.urlshortener.domain.dto.request.UrlRequest;

public interface IUrlShortGenerateService {

    String generate(final UrlRequest urlRequest);
}
