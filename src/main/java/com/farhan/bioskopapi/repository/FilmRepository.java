package com.farhan.bioskopapi.repository;

import com.farhan.bioskopapi.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, String>{

    @Query("SELECT f From FilmEntity f WHERE f.filmName = :name")
    FilmEntity findByName(@PathParam("name") String name);

    @Query("SELECT f FROM FilmEntity f WHERE f.filmStatus = :status")
    List<FilmEntity> findByStatus(@PathParam("status") Boolean status);
}
