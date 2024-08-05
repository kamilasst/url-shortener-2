package com.challenge.urlshortener.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "URL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ORIGINAL_URL", nullable = false)
    private String originalUrl;
    @Column(name = "SHORT_URL", nullable = false)
    private String shortUrl;
    @Column(name = "DATE_CREATED", nullable = false)
    private LocalDateTime dateCreated;
    @OneToMany(mappedBy = "url")
    private Set<Access> accesses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return Objects.equals(originalUrl, url.originalUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalUrl);
    }
}
