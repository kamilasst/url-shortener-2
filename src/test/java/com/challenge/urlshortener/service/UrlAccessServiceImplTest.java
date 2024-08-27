package com.challenge.urlshortener.service;

import com.challenge.urlshortener.domain.dto.response.UrlAccessResponse;
import com.challenge.urlshortener.domain.model.Access;
import com.challenge.urlshortener.domain.model.Url;
import com.challenge.urlshortener.exception.ErrorMessageConstants;
import com.challenge.urlshortener.mock.MockConstants;
import com.challenge.urlshortener.repository.IUrlAccessRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UrlAccessServiceImplTest {

    @InjectMocks
    private UrlAccessServiceImpl urlAccessService;

    @Mock
    private IUrlAccessRepository urlAccessRepository;

    @Test
    public void shouldRegisterSuccess() {
        // arrange
        Url url = Url.builder()
                .originalUrl(MockConstants.WWW_GOOGLE_COM)
                .shortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)
                .build();

        // act
        urlAccessService.register(url);

        //assert
        verify(urlAccessRepository, times(1)).save(any(Access.class));
    }
    //
    @Test
    public void shouldGetStatisticsSuccess() {

        // arrange
        when(urlAccessRepository.existsByShortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)).thenReturn(true);
        when(urlAccessRepository.findCountByShortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)).thenReturn(MockConstants.STATISTICS_10);
        when(urlAccessRepository.findCountDays(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)).thenReturn(MockConstants.STATISTICS_5);

        // act
        UrlAccessResponse response = urlAccessService.getStatistics(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED);

        // assert
        Assertions.assertEquals(MockConstants.STATISTICS_10, response.getTotalAccesses());
        Assertions.assertEquals(MockConstants.STATISTICS_2, response.getAverageAccessPerDay());
    }

    @Test
    public void shouldGetStatisticsEntityNotFoundException() {

        //arrange
        when(urlAccessRepository.existsByShortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)).thenReturn(false);

        //assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            urlAccessService.getStatistics(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED);
        }, ErrorMessageConstants.MESSAGE_03_SHORT_URL_NOT_FOUND);
    }

}
