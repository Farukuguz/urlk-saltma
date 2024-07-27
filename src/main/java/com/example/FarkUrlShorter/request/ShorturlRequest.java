package com.example.FarkUrlShorter.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor


public class ShorturlRequest {

    @NotEmpty
    @NotNull
    private String url;


    private String code;

}
