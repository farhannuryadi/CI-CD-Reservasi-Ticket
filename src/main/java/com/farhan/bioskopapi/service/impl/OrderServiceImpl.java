package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.ScheduleEntity;
import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.repository.OrderRepository;
import com.farhan.bioskopapi.repository.ScheduleRepository;
import com.farhan.bioskopapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ScheduleRepository scheduleRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ScheduleRepository scheduleRepository) {
        this.orderRepository = orderRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<OrderEntity> findByUsername(UserEntity user) {
        return orderRepository.findByUsername(user);
    }

    @Override
    public void createOrder(Long scheduleId, String username, List<String> seats){
        int quantity = seats.size();
        BigDecimal totalPrice = new BigDecimal(0);
        Optional<ScheduleEntity> schedule = scheduleRepository.findById(scheduleId);
        if (schedule.isPresent()) {
            totalPrice = schedule.get().getPrice();
            totalPrice = totalPrice.multiply(BigDecimal.valueOf(quantity));
        }
        orderRepository.createOrder(quantity, totalPrice, scheduleId, username);
    }
}
