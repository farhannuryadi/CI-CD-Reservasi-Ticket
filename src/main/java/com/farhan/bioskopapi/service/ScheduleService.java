package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.ScheduleEntity;

public interface ScheduleService {

    ScheduleEntity save(ScheduleEntity scheduleEntity);
    ScheduleEntity findOne(Long id);
    Iterable<ScheduleEntity> findAll();
    void removeOne(Long id);
}
