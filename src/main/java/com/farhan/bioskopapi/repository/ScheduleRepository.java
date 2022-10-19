package com.farhan.bioskopapi.repository;

import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query("SELECT s FROM ScheduleEntity s WHERE s.film = :film")
    List<ScheduleEntity> findByFilmName(FilmEntity film);

    @Query(value = "SELECT schedule_id FROM schedules WHERE schedule_id = :schdeuleId", nativeQuery = true)
    Long findStudioIdByScheduleId(Long schdeuleId);
}
