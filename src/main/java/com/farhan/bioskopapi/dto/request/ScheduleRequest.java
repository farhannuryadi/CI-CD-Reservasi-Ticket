package com.farhan.bioskopapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {

    private String filmId;
    private Long studioId;
    private LocalDate tanggalTayang;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;
    private BigDecimal harga;
}
