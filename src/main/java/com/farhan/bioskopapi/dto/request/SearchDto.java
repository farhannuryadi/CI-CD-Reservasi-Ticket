package com.farhan.bioskopapi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SearchDto {

    @NotEmpty(message = "searchKey is required")
    private String searchKey;
}
