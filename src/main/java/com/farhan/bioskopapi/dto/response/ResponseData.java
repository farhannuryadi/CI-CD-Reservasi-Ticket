package com.farhan.bioskopapi.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseData<T> {

    private Integer statusCode;
    private Boolean status;
    private List<String> messages = new ArrayList<>();
    private T data;
}