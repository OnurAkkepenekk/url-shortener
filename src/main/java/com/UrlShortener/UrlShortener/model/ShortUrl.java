package com.UrlShortener.UrlShortener.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String url;

    @NotNull
    // Tekrarlamayi engeller
    @Column(unique = true)
    private String code;

}
