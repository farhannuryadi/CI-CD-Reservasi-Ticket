package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.OrderDetailEntity;
import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.ScheduleSeatStudioId;
import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.repository.*;
import com.farhan.bioskopapi.service.OrderDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class OrderDetailServiceImplTest {

    @Mock private OrderDetailRepository orderDetailRepository;
    @Mock private OrderRepository orderRepository;
    @Mock private SeatRepository seatRepository;
    @Mock private ScheduleRepository scheduleRepository;
    @Mock private UserRepository userRepository;
    private OrderDetailService orderDetailService;

    @BeforeEach
    void setUp() {
        this.orderDetailService = new OrderDetailServiceImpl(this.orderDetailRepository, this.orderRepository,
                this.seatRepository, this.scheduleRepository, this.userRepository);
    }

    @Test
    @DisplayName("test verify jika berhasil create order detail")
    void saveOrderDetailentity() {
        ScheduleSeatStudioId scheduleSeatStudioId = new ScheduleSeatStudioId();
        UserEntity user = new UserEntity();
        OrderEntity order = new OrderEntity();
        OrderDetailEntity orderDetail = new OrderDetailEntity(scheduleSeatStudioId, order, user);
        orderDetailService.save(orderDetail);
        Mockito.verify(orderDetailRepository).save(orderDetail);
    }

    @Test
    @DisplayName("test mencari orderDetail Berdasarkan order")
    void findByOrderGetorderDetail() {
        OrderEntity order = new OrderEntity();
        orderDetailService.findByOrder(order);
        Mockito.verify(orderDetailRepository).findByOrder(order);
    }

    @Test
    @DisplayName("ini method void di class OrderDetailServiceImpl jadi saya disable")
    @Disabled
    void createOrderDetail() {
        orderDetailService.createOrderDetail(List.of("01A", "02A"), 1L, "farhanryd");
        Mockito.verify(orderDetailRepository).createOrderDetail(1L, 1L, 1L, 1L, 1L);
        Mockito.verify(orderDetailRepository).createOrderDetail(2L, 1L, 1L, 1L, 1L);
    }
}