package com.farhan.bioskopapi.repository;

import com.farhan.bioskopapi.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, String>{
}
