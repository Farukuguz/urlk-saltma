package com.example.FarkUrlShorter.request.converter;

import com.example.FarkUrlShorter.model.ShortUrl;
import com.example.FarkUrlShorter.request.ShorturlRequest;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlRequsetConverter {

    public ShortUrl convertToEntity(ShorturlRequest shorturlRequest){
        return ShortUrl.builder()
                .url(shorturlRequest.getUrl())
                .code(shorturlRequest.getCode())
                .build();
    }
}
