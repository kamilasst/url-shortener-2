package com.challenge.urlshortener.service;

import com.challenge.urlshortener.domain.dto.request.UrlRequest;
import com.challenge.urlshortener.mock.MockConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UrlShortGenerateServiceImplTest {

    @Autowired
    private UrlShortGenerateServiceImpl urlShortGenerateService;

    @Test
    public void shouldGenerateSuccess() {

        // arrange
        UrlRequest urlRequest = UrlRequest.builder().originalUrl(MockConstants.WWW_GOOGLE_COM).build();

        // act
        String shortenedUrl = urlShortGenerateService.generate(urlRequest);

        // assert
        Assertions.assertNotNull(shortenedUrl);
        Assertions.assertEquals(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED, shortenedUrl);
    }

    @Test
    public void shouldGenerateEqualsUrl() {

        // arrange
        UrlRequest urlRequestGoogle = UrlRequest.builder().originalUrl(MockConstants.WWW_GOOGLE_COM).build();
        UrlRequest urlRequestGoogle2 = UrlRequest.builder().originalUrl(MockConstants.WWW_GOOGLE_COM).build();

        // act
        String shortenedUrlGoogle = urlShortGenerateService.generate(urlRequestGoogle);
        String shortenedUrlGoogle2 = urlShortGenerateService.generate(urlRequestGoogle2);

        // assert
        Assertions.assertEquals(shortenedUrlGoogle, shortenedUrlGoogle2);
    }

    @Test
    public void shouldGenerateNotEqualsUrl() {

        // arrange
        UrlRequest urlRequestGoogle = UrlRequest.builder().originalUrl(MockConstants.WWW_GOOGLE_COM).build();
        UrlRequest urlRequestUol = UrlRequest.builder().originalUrl(MockConstants.WWW_UOL_COM).build();

        // act
        String shortenedUrlGoogle = urlShortGenerateService.generate(urlRequestGoogle);
        String shortenedUrlUol = urlShortGenerateService.generate(urlRequestUol);

        // assert
        Assertions.assertNotEquals(shortenedUrlGoogle, shortenedUrlUol);
    }
}
