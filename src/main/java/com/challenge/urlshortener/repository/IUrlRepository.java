package com.challenge.urlshortener.repository;

import com.challenge.urlshortener.domain.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUrlRepository extends JpaRepository<Url,Integer> {

    Optional<Url> findByOriginalUrl(final String originalUrl);

    Optional<Url> findByShortUrl(final String shortUrl);

}
