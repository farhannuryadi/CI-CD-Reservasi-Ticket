package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.ScheduleEntity;
import com.farhan.bioskopapi.repository.ScheduleRepository;
import com.farhan.bioskopapi.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleEntity save(ScheduleEntity scheduleEntity) {
        return scheduleRepository.save(scheduleEntity);
    }

    @Override
    public ScheduleEntity findOne(Long id) {
        Optional<ScheduleEntity> scheduleEntity = scheduleRepository.findById(id);
        if (scheduleEntity.isEmpty()) {
            return null;
        }
        return scheduleEntity.get();
    }

    @Override
    public Iterable<ScheduleEntity> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public void removeOne(Long id) {
        scheduleRepository.deleteById(id);
    }
}
