package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.OrderDetailEntity;
import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.repository.*;
import com.farhan.bioskopapi.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final SeatRepository seatRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository,
                                  SeatRepository seatRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.seatRepository = seatRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OrderDetailEntity save(OrderDetailEntity orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetailEntity> findByOrder(OrderEntity order) {
        return orderDetailRepository.findByOrder(order);
    }

    @Override
    public void createOrderDetail(List<String> seats, Long scheduleId, String username){
        List<Long> seatId = new ArrayList<>();
        Optional<UserEntity> user = userRepository.findByUsername(username);

        Long orderId = orderRepository.findOrderIdByScheduleIdAndUsername(scheduleId, username);
        Long studioId = scheduleRepository.findStudioIdByScheduleId(scheduleId);
        seats.forEach(s -> seatId.add(seatRepository.findSeatIdByName(s)));
        seatId.forEach(idSeat -> orderDetailRepository.createOrderDetail(idSeat, scheduleId, studioId, orderId, user.get().getId()));
    }
}
