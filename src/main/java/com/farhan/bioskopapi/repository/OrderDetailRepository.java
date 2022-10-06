package com.farhan.bioskopapi.repository;

import com.farhan.bioskopapi.entity.OrderDetailEntity;
import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.ScheduleSeatStudioId;
import com.farhan.bioskopapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, ScheduleSeatStudioId> {

    @Query("SELECT od FROM OrderDetailEntity od WHERE od.order = :order")
    List<OrderDetailEntity> findByOrder(OrderEntity order);

    @Query("SELECT od FROM OrderDetailEntity od WHERE od.order = :order AND od.user = :user")
    List<OrderDetailEntity> findByOrderAndUser(OrderEntity order, UserEntity user);
}
