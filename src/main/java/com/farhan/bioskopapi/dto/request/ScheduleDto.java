package com.farhan.bioskopapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    private String filmId;
    private Long studioId;
    private Date tanggalTayang;
    private Date jamMulai;
    private Date jamSelesai;
    private BigDecimal harga;
}
