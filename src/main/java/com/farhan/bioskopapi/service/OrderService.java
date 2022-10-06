package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.UserEntity;

import java.util.List;

public interface OrderService {

    List<OrderEntity> findByUsername(UserEntity user);
    void createOrder(Long scheduleId, String username, List<String> seats);
}
