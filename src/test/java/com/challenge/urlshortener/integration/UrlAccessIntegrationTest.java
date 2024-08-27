package com.challenge.urlshortener.integration;

import com.challenge.urlshortener.domain.model.Access;
import com.challenge.urlshortener.domain.model.Url;
import com.challenge.urlshortener.mock.MockConstants;
import com.challenge.urlshortener.repository.IUrlAccessRepository;
import com.challenge.urlshortener.repository.IUrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UrlAccessIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUrlAccessRepository urlAccessRepository;

    @Autowired
    private IUrlRepository urlRepository;

    @BeforeEach
    public void setUp() {
        urlAccessRepository.deleteAll();
        urlRepository.deleteAll();
    }

    @Test
    public void shouldReturnGetAccessStatistics200Ok() throws Exception {

        //arrange url
        Url url = Url.builder()
                .originalUrl(MockConstants.WWW_GOOGLE_COM)
                .shortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)
                .dateCreated(LocalDateTime.now())
                .build();

        urlRepository.save(url);

        //arrange access
        saveAccess(url,2024, 8, 20, 13,42);
        saveAccess(url, 2024, 8, 21, 13,42);
        saveAccess(url, 2024, 8, 22, 13,42);
        saveAccess(url, 2024, 8, 22, 13,42);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(MockConstants.GET_API_ACCESS_STATISTICS+ url.getShortUrl()))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        //assert
        Assertions.assertEquals(MockConstants.MESSAGE_URL_RESPONSE_JSON_TOTAL_ACCESS_AVERAGE_ACCESS_PER_DAY, mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void shouldReturnGetAccessStatistics404NotFound() throws Exception {

        //arrange
        String shortUrl = MockConstants.SHORTENED_URL_NOT_EXIST;

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(MockConstants.GET_API_ACCESS_STATISTICS + shortUrl))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

        //assert
        Assertions.assertEquals(MockConstants.MESSAGE_RESPONSE_SHORTENED_URL_NOT_FOUND, mvcResult.getResponse().getContentAsString());
    }

    private void saveAccess(Url url, int year, int month, int day, int hour, int minute){
        Access access = Access.builder()
                .url(url)
                .dateAccess(LocalDateTime.of(year, month, day, hour, minute))
                .build();

        urlAccessRepository.save(access);

    }
}
