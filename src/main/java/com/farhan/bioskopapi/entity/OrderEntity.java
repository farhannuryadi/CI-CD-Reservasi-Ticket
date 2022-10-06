package com.farhan.bioskopapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Transactional
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @CreationTimestamp
    @Column(name = "date_order", nullable = false)
    private LocalDateTime dateOrder;

    @Column(name = "quantity")
    private Short quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "username", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleEntity schedule;

    @OneToMany(orphanRemoval = true, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetail = new ArrayList<>();
}
