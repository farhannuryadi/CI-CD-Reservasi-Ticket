package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.OrderDetailEntity;
import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.repository.OrderDetailRepository;
import com.farhan.bioskopapi.service.OrderDetailService;
import com.farhan.bioskopapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetailEntity save(OrderDetailEntity orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetailEntity> findByOrder(OrderEntity order) {
        return orderDetailRepository.findByOrder(order);
    }
}
