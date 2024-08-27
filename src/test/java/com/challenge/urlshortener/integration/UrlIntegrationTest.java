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
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UrlIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUrlRepository urlRepository;

    @Autowired
    private IUrlAccessRepository urlAccessRepository;

    @BeforeEach
    public void setUp() {
        urlAccessRepository.deleteAll();
        urlRepository.deleteAll();
    }

    @Test
    public void shouldReturnCreateShortenedUrl201Created() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(MockConstants.POST_API_URL_SHORTEN)
                        .contentType(MockConstants.APPLICATION_JSON)
                        .content(MockConstants.URL_REQUEST_JSON_WWW_GOOGLE_COM))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        Assertions.assertNotNull(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED, mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void shouldReturnCreateShortenedUrl409Conflict() throws Exception {

        // arrange
        Url url = Url.builder()
                .originalUrl(MockConstants.WWW_GOOGLE_COM)
                .shortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)
                .dateCreated(LocalDateTime.now())
                .build();
        urlRepository.save(url);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(MockConstants.POST_API_URL_SHORTEN)
                        .contentType(MockConstants.APPLICATION_JSON)
                        .content(MockConstants.URL_REQUEST_JSON_WWW_GOOGLE_COM))
                .andExpect(MockMvcResultMatchers.status().isConflict()).andReturn();

        Assertions.assertEquals(MockConstants.MESSAGE_RESPONSE_SHORTENED_URL_HAS_ALREADY_BEEN_CREATED, mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void  shouldReturnCreateShortenedUrl400BadRequest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(MockConstants.POST_API_URL_SHORTEN)
                        .contentType(MockConstants.APPLICATION_JSON)
                        .content(MockConstants.URL_REQUEST_JSON_INVALID))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        Assertions.assertEquals(MockConstants.MESSAGE_URL_RESPONSE_JSON_ORIGINAL_URL_MUST_BE_A_VALID_URL, mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void shouldReturnGetOriginalUrl200Ok() throws Exception {

        //arrange
        Url url = Url.builder()
                .originalUrl(MockConstants.WWW_GOOGLE_COM)
                .shortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)
                .dateCreated(LocalDateTime.now())
                .build();
        urlRepository.save(url);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(MockConstants.GET_API_URL + url.getShortUrl()))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        //assert
        List<Access> accessDataBase = urlAccessRepository.findAll();

        Assertions.assertEquals(MockConstants.WWW_GOOGLE_COM, mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(url, accessDataBase.get(0).getUrl());
    }

    @Test
    public void shouldReturnGetOriginalUrl404NotFound() throws Exception {

        //arrange
        Url url = Url.builder()
                .originalUrl(MockConstants.WWW_GOOGLE_COM)
                .shortUrl(MockConstants.SHORTENED_URL_BY_GOOGLE_EXPECTED)
                .dateCreated(LocalDateTime.now())
                .build();
        urlRepository.save(url);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(MockConstants.GET_API_URL +
                        MockConstants.SHORTENED_URL_NOT_EXIST))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

        //assert
        Assertions.assertEquals(MockConstants.MESSAGE_RESPONSE_ENTITY_NOT_FOUND, mvcResult.getResponse().getContentAsString());
    }

}
