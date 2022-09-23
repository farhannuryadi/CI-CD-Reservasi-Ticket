package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.FilmEntity;

public interface FilmService {
    FilmEntity save(FilmEntity filmEntity);
    FilmEntity findOne(String id);
    Iterable<FilmEntity> findAll();
    void removeOne(String id);
}
