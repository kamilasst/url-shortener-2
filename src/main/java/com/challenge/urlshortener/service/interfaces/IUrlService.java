package com.challenge.urlshortener.service.interfaces;

import com.challenge.urlshortener.domain.dto.request.UrlRequest;

public interface IUrlService {

    String createShortenedUrl(final UrlRequest urlRequest);

    String getOriginalUrl(final String shortUrl);

}