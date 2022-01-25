package com.UrlShortener.UrlShortener.service;

import com.UrlShortener.UrlShortener.dto.ShortUrlDto;
import com.UrlShortener.UrlShortener.exception.CodeAlreadyExists;
import com.UrlShortener.UrlShortener.exception.ShortUrlNotFoundException;
import com.UrlShortener.UrlShortener.model.ShortUrl;
import com.UrlShortener.UrlShortener.repository.ShortUrlRepository;
import com.UrlShortener.UrlShortener.util.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final RandomStringGenerator randomStringGenerator;

    public ShortUrlService(ShortUrlRepository shortUrlRepository, RandomStringGenerator randomStringGenerator) {
        this.shortUrlRepository = shortUrlRepository;
        this.randomStringGenerator = randomStringGenerator;
    }

    public List<ShortUrl> getAllShortUrl() {
        return shortUrlRepository.findAll();
    }

    public ShortUrl getUrlByCode(String code) {
        return shortUrlRepository.findAllByCode(code).orElseThrow(
                () -> new ShortUrlNotFoundException("URL not found")
        );
    }

    public ShortUrl create(ShortUrl shortUrl) {
        if (shortUrl.getCode() == null || shortUrl.getCode().isEmpty()) {
            shortUrl.setCode(generateCode());
        } else if (shortUrlRepository.findAllByCode(shortUrl.getCode()).isPresent()) {
            throw new CodeAlreadyExists("Code already exists");
        }
        shortUrl.setCode(shortUrl.getCode().toUpperCase());
        return shortUrlRepository.save(shortUrl);
    }

    // Ayni code varolduğu surece generate et diyoruz
    private String generateCode() {
        String code;
        do {
            code = randomStringGenerator.generateRandomString();
        } while (shortUrlRepository.findAllByCode(code).isPresent());

        return code;
    }


}
