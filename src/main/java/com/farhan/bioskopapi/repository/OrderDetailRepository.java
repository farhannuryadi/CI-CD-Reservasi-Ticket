package com.farhan.bioskopapi.repository;

import com.farhan.bioskopapi.entity.OrderDetailEntity;
import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.ScheduleSeatStudioId;
import com.farhan.bioskopapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, ScheduleSeatStudioId> {

    @Query("SELECT od FROM OrderDetailEntity od WHERE od.order = :order")
    List<OrderDetailEntity> findByOrder(OrderEntity order);

    @Query("SELECT od FROM OrderDetailEntity od WHERE od.order = :order AND od.user = :user")
    List<OrderDetailEntity> findByOrderAndUser(OrderEntity order, UserEntity user);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO order_detail (seat_id, schedule_id, studio_id, order_id, username)\n" +
            "VALUES (:seatId, :scheduleId, :studioId, :orderId, :userId)", nativeQuery = true)
    void createOrderDetail(Long seatId, Long scheduleId, Long studioId, Long orderId, Long userId);
}