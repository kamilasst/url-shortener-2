package com.challenge.urlshortener.service;

import com.challenge.urlshortener.domain.dto.request.UrlRequest;
import com.challenge.urlshortener.domain.model.Access;
import com.challenge.urlshortener.domain.model.Url;
import com.challenge.urlshortener.exception.EntityAlreadyExistsException;
import com.challenge.urlshortener.exception.ErrorMessageConstants;
import com.challenge.urlshortener.mock.MockConstants;
import com.challenge.urlshortener.repository.IUrlRepository;
import com.challenge.urlshortener.service.interfaces.IUrlAccessService;
import com.challenge.urlshortener.service.interfaces.IUrlShortGenerateService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UrlServiceImplTest {

    @InjectMocks
    private UrlServiceImpl urlService;

    @Mock
    private IUrlRepository urlRepositoryMock;
    @Mock
    private IUrlShortGenerateService urlShortGenerateServiceMock;
    @Mock
    private IUrlAccessService urlAccessServiceMock;

    @Test
    public void shouldCreateShortenedUrlSuccess() {

        // arrange
        UrlRequest urlRequest = UrlRequest.builder().originalUrl(MockConstants.WWW_GOOGLE_COM).build();

        when(urlRepositoryMock.findByOriginalUrl(urlRequest.getOriginalUrl())).thenReturn(Optional.empty());
        when(urlShortGenerateServiceMock.generate(urlRequest)).thenReturn(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED);
        when(urlRepositoryMock.save(any(Url.class))).thenReturn(any(Url.class));

        // act
        String shortenedUrl = urlService.createShortenedUrl(urlRequest);

        // assert
        Assertions.assertEquals(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED, shortenedUrl);
    }

    @Test
    public void shouldCreateShortenedUrlEntityAlreadyExistsException() {

        // arrange
        UrlRequest urlRequest = UrlRequest.builder().originalUrl(MockConstants.WWW_GOOGLE_COM).build();

        Url existingUrl = Url.builder()
                .originalUrl(MockConstants.WWW_GOOGLE_COM)
                .shortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)
                .dateCreated(LocalDateTime.now())
                .build();
        when(urlRepositoryMock.findByOriginalUrl(urlRequest.getOriginalUrl())).thenReturn(Optional.of(existingUrl));

        // act
        //assert
        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> {
            urlService.createShortenedUrl(urlRequest);
        }, String.format(ErrorMessageConstants.MESSAGE_01_ORIGINAL_URL_ALREADY_EXIST,
                existingUrl.getOriginalUrl(),
                existingUrl.getShortUrl()));
    }


    @Test
    public void shouldGetOriginalUrlEntityNotFoundException() {

        //arrange
        when(urlRepositoryMock.findByShortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)).thenReturn(Optional.empty());

        // act
        //assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            urlService.getOriginalUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED);
        }, ErrorMessageConstants.MESSAGE_02_NOT_FOUND);
    }

    @Test
    public void shouldGetOriginalUrlEntitySuccess() {

        //arrange
        Url existingUrl = Url.builder()
                .originalUrl(MockConstants.WWW_GOOGLE_COM)
                .shortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)
                .dateCreated(LocalDateTime.now())
                .build();
        when(urlRepositoryMock.findByShortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)).thenReturn(Optional.of(existingUrl));

        // act
        String originalUrl = urlService.getOriginalUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED);

        // assert
        Assertions.assertEquals(MockConstants.WWW_GOOGLE_COM, originalUrl);
        verify(urlAccessServiceMock, times(1)).register(existingUrl);
    }
}
