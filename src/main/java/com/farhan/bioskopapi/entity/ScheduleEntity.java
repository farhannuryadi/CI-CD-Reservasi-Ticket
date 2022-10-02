package com.farhan.bioskopapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "schedules")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ScheduleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private StudioEntity studio;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date tanggalTayang;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date jamMulai;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date jamSelesai;

    @Column(nullable = false)
    private BigDecimal harga;
}
