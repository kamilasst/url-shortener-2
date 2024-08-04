package com.challenge.urlshortener.service.interfaces;

import com.challenge.urlshortener.domain.model.Url;
import com.challenge.urlshortener.domain.dto.response.UrlAccessResponse;

public interface IUrlAccessService {

    void register(final Url url);

    UrlAccessResponse getStatistics(final String shortUrl);

}
