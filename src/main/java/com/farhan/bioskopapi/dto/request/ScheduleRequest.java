package com.farhan.bioskopapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {

    private String filmId;
    @NotEmpty
    private Long studioId;
    @NotEmpty
    private LocalDate tanggalTayang;
    @NotEmpty
    private LocalTime jamMulai;
    @NotEmpty
    private LocalTime jamSelesai;
    @NotEmpty
    private BigDecimal harga;
}
