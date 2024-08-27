package com.challenge.urlshortener.repository;

import com.challenge.urlshortener.domain.model.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IUrlAccessRepository extends JpaRepository<Access, Integer> {

    @Query("SELECT COUNT(a) FROM Access a WHERE a.url.shortUrl = :shortUrl")
    Integer findCountByShortUrl(@Param("shortUrl") String shortUrl);

    @Query(value = "SELECT COUNT(DISTINCT DATE_TRUNC('day', a.date_access)) FROM ACCESS a JOIN URL u ON a.url_id = u.id WHERE u.short_url = :shortUrl", nativeQuery = true)
    Integer findCountDays(@Param("shortUrl") String shortUrl);

    @Query("SELECT COUNT(a) > 0 FROM Access a WHERE a.url.shortUrl = :shortUrl")
    boolean existsByShortUrl(String shortUrl);
}
