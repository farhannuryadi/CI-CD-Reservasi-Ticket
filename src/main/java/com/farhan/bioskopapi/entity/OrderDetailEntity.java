package com.farhan.bioskopapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Transactional
@Table(name = "order_detail")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDetailEntity {

    @EmbeddedId
    private ScheduleSeatStudioId scheduleSeatStudioId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "order_id")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "username", nullable = false)
    private UserEntity user;
}
