package com.example.FarkUrlShorter.dto.converter;


import com.example.FarkUrlShorter.dto.ShortUrlDto;
import com.example.FarkUrlShorter.model.ShortUrl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShortUrlDtoConverter {
    public ShortUrlDto convertToDto(ShortUrl shortUrl){
        return ShortUrlDto.builder()
                        .id(shortUrl.getId())
                .url(shortUrl.getUrl())
                .code(shortUrl.getCode())
                .build();
    }
    public List<ShortUrlDto> convertToDto(List<ShortUrl> shortUrl){
        return shortUrl.stream()
                .map(x -> convertToDto(x))
                .collect(Collectors.toList());

    }



}
