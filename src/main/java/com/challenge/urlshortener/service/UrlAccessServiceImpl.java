package com.challenge.urlshortener.service;

import com.challenge.urlshortener.domain.model.Access;
import com.challenge.urlshortener.domain.model.Url;
import com.challenge.urlshortener.domain.dto.response.UrlAccessResponse;
import com.challenge.urlshortener.repository.IUrlAccessRepository;
import com.challenge.urlshortener.service.interfaces.IUrlAccessService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlAccessServiceImpl implements IUrlAccessService {

    private final IUrlAccessRepository urlAccessRepository;

    public UrlAccessServiceImpl(final IUrlAccessRepository urlAccessRepository) {
        this.urlAccessRepository = urlAccessRepository;
    }

    @Override
    public void register(final Url url) {
        Access access = Access.builder().url(url).dateAccess(LocalDateTime.now()).build();
        urlAccessRepository.save(access);
    }

    @Override
    public UrlAccessResponse getStatistics(final String shortUrl) {
        Integer total = getTotal(shortUrl);
        return UrlAccessResponse.builder()
                .totalAccesses(total)
                .averageAccessPerDay(getAveragePerDay(shortUrl, total))
                .build();
    }

    private Integer getTotal(final String shortUrl) {
        return urlAccessRepository.findCountByShortUrl(shortUrl);
    }

    private Double getAveragePerDay(final String shortUrl, final Integer total) {
        Integer totalDays = urlAccessRepository.findCountDays(shortUrl);
        return total == 0 && totalDays == 0 ? 0 : (double) total / totalDays;
    }

}
