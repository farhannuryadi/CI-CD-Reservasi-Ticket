package com.farhan.bioskopapi.dto.request;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class StudioRequest {

    private Long id;
    @NotEmpty
    private String studioName;
    @NotEmpty
    private Short maxSeat;
    @NotEmpty
    private Boolean studioStatus;
}
