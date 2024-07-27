package com.example.FarkUrlShorter.controller;


import com.example.FarkUrlShorter.dto.ShortUrlDto;
import com.example.FarkUrlShorter.dto.converter.ShortUrlDtoConverter;
import com.example.FarkUrlShorter.model.ShortUrl;
import com.example.FarkUrlShorter.request.ShorturlRequest;
import com.example.FarkUrlShorter.request.converter.ShortUrlRequsetConverter;
import com.example.FarkUrlShorter.service.ShortUrlService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping
public class UrlController {

    private final ShortUrlDtoConverter shortUrlDtoConverter;
    private final ShortUrlRequsetConverter shortUrlRequsetConverter;
    private final ShortUrlService service;

    public UrlController(ShortUrlDtoConverter shortUrlDtoConverter, ShortUrlRequsetConverter shortUrlRequsetConverter, ShortUrlService service) {
        this.shortUrlDtoConverter = shortUrlDtoConverter;
        this.shortUrlRequsetConverter = shortUrlRequsetConverter;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ShortUrlDto>> getAllUrls(){
        return new ResponseEntity<List<ShortUrlDto>>(
                shortUrlDtoConverter.convertToDto(service.getAllShortUrl()), HttpStatus.OK
        );
    }

    @GetMapping("/show/{code}")
    public ResponseEntity<ShortUrlDto> getUrlByCode(@Valid @NotEmpty @PathVariable String code){
        return new ResponseEntity<ShortUrlDto>(
                shortUrlDtoConverter.convertToDto(service.getUrlByCode(code)), HttpStatus.OK
        );
    }
    @GetMapping("/{code}")
    public ResponseEntity<ShortUrlDto> redirect(@Valid @NotEmpty @PathVariable String code) throws URISyntaxException {

        ShortUrl shortUrl= service.getUrlByCode(code);

        URI uri = new URI(shortUrl.getUrl());

        HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(
                httpHeaders,HttpStatus.SEE_OTHER
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ShorturlRequest shorturlRequest){
        ShortUrl shortUrl= shortUrlRequsetConverter.convertToEntity(shorturlRequest);

        return new ResponseEntity<ShortUrlDto>(shortUrlDtoConverter.convertToDto(service.create(shortUrl)),HttpStatus.CREATED);
    }
}
