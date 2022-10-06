package com.farhan.bioskopapi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class ScheduleSeatStudioId implements Serializable {

//    @Column(name = "schedule_id")
//    private Long scheduleId;
//
//    @Column(name = "seat_id")
//    private Long seatId;
//
//    @Column(name = "studio_id")
//    private Long studioId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity schedule;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private SeatEntity seat;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private StudioEntity studio;
}
