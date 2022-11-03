package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.StudioEntity;

public interface StudioService {

    StudioEntity save(StudioEntity studioEntity);
    StudioEntity findOne(Long id);
    Iterable<StudioEntity> findAll();
    void removeOne(Long id);
}
