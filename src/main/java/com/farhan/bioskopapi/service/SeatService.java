package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.ScheduleEntity;
import com.farhan.bioskopapi.entity.SeatEntity;

import java.util.List;

public interface SeatService {

    List<SeatEntity> findAll();
    List<String> findSeatAvailable(Long scheduleId);
}
