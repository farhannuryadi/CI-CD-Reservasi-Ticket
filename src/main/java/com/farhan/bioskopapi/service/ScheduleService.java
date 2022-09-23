package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.ScheduleEntity;

import java.util.List;

public interface ScheduleService {

    ScheduleEntity save(ScheduleEntity scheduleEntity);
    ScheduleEntity findOne(Long id);
    Iterable<ScheduleEntity> findAll();
    void removeOne(Long id);
    List<ScheduleEntity> findScheduleByFilm(String id);
}
