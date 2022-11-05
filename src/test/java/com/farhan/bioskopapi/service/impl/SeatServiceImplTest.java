package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.SeatEntity;
import com.farhan.bioskopapi.repository.SeatRepository;
import com.farhan.bioskopapi.service.SeatService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SeatServiceImplTest {

    @Autowired
    private SeatService seatService;
    private final SeatRepository seatRepository = Mockito.mock(SeatRepository.class);
    List<SeatEntity> list = new ArrayList<>();

    @BeforeEach
    void setUp(){
        SeatEntity seat1 = new SeatEntity(1L, "01A");
        SeatEntity seat2 = new SeatEntity(2L, "02A");
        list.add(seat1);
        list.add(seat2);
        Mockito.when(seatRepository.findAll()).thenReturn(list);
    }

    @Test
    @DisplayName("test apakah jumlah seat sama dengan 2")
    void findAllSeatEntity() {
        Assertions.assertEquals(2, seatService.findAll().size());
    }

    @Test
    @DisplayName("test ketersedian seat")
    void findSeatAvailableSeatEntity() {
        List<String> seat = new ArrayList<>();
        Long scheduleId = 1L;
        String[] cek = {"01A", "02A"};

        list.stream().forEach(seatEntity -> seat.add(seatEntity.getSeatName()));

        Mockito.when(seatRepository.findSeatAvailable(scheduleId)).thenReturn(seat);
        Assertions.assertArrayEquals(cek, seat.toArray());
    }
}