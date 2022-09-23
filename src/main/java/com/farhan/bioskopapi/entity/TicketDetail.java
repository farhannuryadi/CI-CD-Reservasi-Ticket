package com.farhan.bioskopapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ticket_detail")
public class TicketDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_detail_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "seat_detail_id", nullable = false)
    private SeatsDetailEntity seatsDetail;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketEntity ticket;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserEntity user;
}
