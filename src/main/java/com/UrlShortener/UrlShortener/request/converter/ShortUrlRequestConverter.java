package com.UrlShortener.UrlShortener.request.converter;

import com.UrlShortener.UrlShortener.model.ShortUrl;
import com.UrlShortener.UrlShortener.request.ShortUrlRequest;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlRequestConverter {

    public ShortUrl convertToEntity(ShortUrlRequest shortUrlRequest) {
        return ShortUrl.builder()
                .url(shortUrlRequest.getUrl())
                .code(shortUrlRequest.getCode())
                .build();
    }
}
