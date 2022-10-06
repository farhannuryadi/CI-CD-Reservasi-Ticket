package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.UserEntity;

import java.util.List;

public interface OrderService {

    OrderEntity save(OrderEntity order);
    List<OrderEntity> findByUsername(UserEntity user);
}
