package com.farhan.bioskopapi.repository;

import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o WHERE o.user = :user")
    List<OrderEntity> findByUsername(UserEntity user);
}
