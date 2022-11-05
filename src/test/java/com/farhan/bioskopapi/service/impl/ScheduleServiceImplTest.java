package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.ScheduleEntity;
import com.farhan.bioskopapi.repository.ScheduleRepository;
import com.farhan.bioskopapi.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {

    @Mock private ScheduleRepository scheduleRepository;
    private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        this.scheduleService = new ScheduleServiceImpl(this.scheduleRepository);
    }

    @Test
    @DisplayName("test jika berhasil create schedule")
    void saveScheduleEntity() {
        ScheduleEntity schedule = new ScheduleEntity();
        scheduleService.save(schedule);
        Mockito.verify(scheduleRepository).save(schedule);
    }

    @Test
    @DisplayName("test cari schedule berdasarkan id")
    void findOneSchedule() {
        scheduleService.findOne(1L);
        Mockito.verify(scheduleRepository).findById(1L);
    }

    @Test
    @DisplayName("test tampilkan semua schedule")
    void findAllSchedule() {
        scheduleService.findAll();
        Mockito.verify(scheduleRepository).findAll();
    }

    @Test
    @DisplayName("test cari schedule berdasarkan film name")
    void findByFilmNameSchedule() {
        FilmEntity film = new FilmEntity();
        scheduleService.findByFilmName(film);
        Mockito.verify(scheduleRepository).findByFilmName(film);
    }
}