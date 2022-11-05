package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.SeatEntity;
import com.farhan.bioskopapi.repository.SeatRepository;
import com.farhan.bioskopapi.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<SeatEntity> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public List<String> findSeatAvailable(Long scheduleId) {
        return seatRepository.findSeatAvailable(scheduleId);
    }

}
