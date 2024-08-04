package com.challenge.urlshortener.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ACCESS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "URL_ID", nullable = false)
    private Url url;
    @Column(name = "DATE_ACCESS", nullable = false)
    private LocalDateTime dateAccess;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Access access = (Access) o;
        return Objects.equals(url, access.url) && Objects.equals(dateAccess, access.dateAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, dateAccess);
    }
}
