package com.farhan.bioskopapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FilmRequest {
    @NotEmpty(message = "Id is required")
    private String id;
    @NotEmpty
    private String filmName;
    @NotEmpty
    private Boolean filmStatus;
}
