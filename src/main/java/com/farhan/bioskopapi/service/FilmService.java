package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.FilmEntity;

import java.util.List;

public interface FilmService {
    FilmEntity save(FilmEntity filmEntity);
    FilmEntity findOne(String id);
    Iterable<FilmEntity> findAll();
    void removeOne(String id);
    FilmEntity findByName(String name);
    List<FilmEntity> findByStatus(Boolean status);
}
