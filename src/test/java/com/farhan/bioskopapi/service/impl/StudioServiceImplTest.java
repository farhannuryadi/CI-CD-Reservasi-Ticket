package com.farhan.bioskopapi.service.impl;



import com.farhan.bioskopapi.entity.StudioEntity;
import com.farhan.bioskopapi.repository.StudioRepository;
import com.farhan.bioskopapi.service.StudioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudioServiceImplTest {

    @Autowired
    private StudioService studioService;

    private final StudioRepository studioRepository = Mockito.mock(StudioRepository.class);
    StudioEntity studioEntity;

    @BeforeEach
    void setUp(){
        studioEntity = new StudioEntity(1L, "Studio A", (short) 20, true);
        Mockito.when(studioRepository.save(new StudioEntity(1L, "Studio A", (short) 20, true))).thenReturn(studioEntity);
        Mockito.when(studioRepository.findAll()).thenReturn(List.of(studioEntity));
    }

    @Test
    @DisplayName("test jika berhasil create studio")
    void saveStudioEntitySuccess() {
        StudioEntity studioEntity1 = new StudioEntity(1L, "Studio A", (short) 20, true);
        Assertions.assertEquals(studioEntity1.getStudioName(), studioService.save(studioEntity1).getStudioName());
    }

    @Test
    @DisplayName("test ambil semua data studio tidak null")
    void findAllStudiEntity() {
        Iterable<StudioEntity> studioEntities = studioService.findAll();
        Assertions.assertNotNull(studioEntities);
    }
}