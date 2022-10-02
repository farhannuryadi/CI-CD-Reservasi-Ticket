package com.farhan.bioskopapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ScheduleDto {

    private String filmId;
    private Long studioId;
    private Date tanggalTayang;
    private Date jamMulai;
    private Date jamSelesai;
    private BigDecimal harga;
}
