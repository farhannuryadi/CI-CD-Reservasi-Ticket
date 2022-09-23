package com.farhan.bioskopapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "seats_detail")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SeatsDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private SeatEntity seat;

    @ManyToOne
    @JoinColumn(name = "studio_id", nullable = false)
    private StudioEntity studio;
}
