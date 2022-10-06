package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.OrderDetailEntity;
import com.farhan.bioskopapi.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderDetailService {

    OrderDetailEntity save(OrderDetailEntity orderDetail);
    List<OrderDetailEntity> findByOrder(OrderEntity order);
    void createOrderDetail(List<String> seats, Long scheduleId, String username);
}
