package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.OrderEntity;
import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.repository.OrderRepository;
import com.farhan.bioskopapi.repository.ScheduleRepository;
import com.farhan.bioskopapi.repository.UserRepository;
import com.farhan.bioskopapi.service.OrderService;
import com.farhan.bioskopapi.service.UserService;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock private OrderRepository orderRepository;
    @Mock private ScheduleRepository scheduleRepository;
    @Mock private UserRepository userRepository;;
    private OrderService orderService;
    UserEntity user;

    @BeforeEach
    void setUp() {
        this.orderService = new OrderServiceImpl(this.orderRepository, this.scheduleRepository,
                this.userRepository);
        UserEntity user = new UserEntity("farhanryd", "Farhan Nuryadi", "Depok",
                "farhan@gmail.com", "123456");
    }

    @Test
    @DisplayName("test mencari userEntity berdasarkan username")
    void findByUsernameTest() {
        orderService.findByUsername(user);
        Mockito.verify(orderRepository).findByUsername(user);
    }

    @Test
    @DisplayName("ini method void di class OrderServiceImpl jadi saya disable")
    @Disabled
    void createOrderTest() {
        UserEntity user1 = new UserEntity("farhanryd", "Farhan Nuryadi", "Depok",
                "farhan@gmail.com", "123456");
        userRepository.save(user1);
        int quantity = 2;
        BigDecimal totalPrice = new BigDecimal(70000);
        Long scheduleId = 1L;
        Long userId = user1.getId();
        String username = user1.getUsername();
        List<String> seats = List.of("01A", "02A");
        orderService.createOrder(scheduleId, username, seats);
        Mockito.verify(orderRepository).createOrder(quantity, totalPrice, scheduleId, userId);
    }
}