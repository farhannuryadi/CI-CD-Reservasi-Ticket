package com.farhan.bioskopapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "studios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id")
    private Long id;

    @Column(name = "studio_name", nullable = false, length = 25)
    private String studioName;

    @Column(name = "max_seat")
    private Short maxSeat;

    @Column(name = "studio_status", nullable = false)
    private Boolean studioStatus;
}
