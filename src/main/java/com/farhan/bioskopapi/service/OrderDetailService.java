package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.OrderDetailEntity;
import com.farhan.bioskopapi.entity.OrderEntity;

import java.util.List;

public interface OrderDetailService {

    OrderDetailEntity save(OrderDetailEntity orderDetail);
    List<OrderDetailEntity> findByOrder(OrderEntity order);
}
