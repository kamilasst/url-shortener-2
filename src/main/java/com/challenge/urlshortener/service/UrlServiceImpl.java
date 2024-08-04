package com.challenge.urlshortener.service;

import com.challenge.urlshortener.domain.dto.request.UrlRequest;
import com.challenge.urlshortener.exception.ErrorMessageConstants;
import com.challenge.urlshortener.exception.EntityAlreadyExistsException;
import com.challenge.urlshortener.domain.model.Url;
import com.challenge.urlshortener.repository.IUrlRepository;
import com.challenge.urlshortener.service.interfaces.IUrlService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlServiceImpl implements IUrlService {

    private final IUrlRepository urlRepository;
    private final UrlShortGenerateServiceImpl urlShortGenerateService;
    private final UrlAccessServiceImpl urlAccessService;

    public UrlServiceImpl(final IUrlRepository urlRepository,
                          final UrlShortGenerateServiceImpl urlShortGenerateService,
                          final UrlAccessServiceImpl urlAccessService){

        this.urlRepository = urlRepository;
        this.urlShortGenerateService = urlShortGenerateService;
        this.urlAccessService = urlAccessService;
    }
    @Override
    public String createShortenedUrl(final UrlRequest urlRequest) {

        validateOriginalUrlExist(urlRequest);
        String shortenedUrl = urlShortGenerateService.generate(urlRequest);
        Url url = Url.builder()
                .originalUrl(urlRequest.getOriginalUrl())
                .shortUrl(shortenedUrl)
                .dateCreated(LocalDateTime.now())
                .build();
        urlRepository.save(url);
        return shortenedUrl;
    }

    @Override
    public String getOriginalUrl(final String shortUrl) {
        Optional<Url> optionalUrl = urlRepository.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()){
            urlAccessService.register(optionalUrl.get());
            return optionalUrl.get().getOriginalUrl();
        }
        throw new EntityNotFoundException(ErrorMessageConstants.MESSAGE_02_NOT_FOUND);
    }

    private void validateOriginalUrlExist(final UrlRequest urlRequest) {
        Optional<Url> optionalUrl = urlRepository.findByOriginalUrl(urlRequest.getOriginalUrl());
        if(optionalUrl.isPresent()){
            throw new EntityAlreadyExistsException(String.format(ErrorMessageConstants.MESSAGE_01_ORIGINAL_URL_ALREADY_EXIST,
                    optionalUrl.get().getOriginalUrl(),
                    optionalUrl.get().getShortUrl()));
        }
    }

}
