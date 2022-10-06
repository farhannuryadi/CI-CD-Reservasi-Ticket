package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.repository.OrderRepository;
import com.farhan.bioskopapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> findByUsername(UserEntity user) {
        return orderRepository.findByUsername(user);
    }
}
