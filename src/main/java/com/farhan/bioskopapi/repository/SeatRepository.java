package com.farhan.bioskopapi.repository;

import com.farhan.bioskopapi.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    @Query(
            value = "SELECT seat_name " +
                    "FROM seats s " +
                    "WHERE NOT EXISTS " +
            "(SELECT * FROM order_detail od " +
            "WHERE od.seat_id=s.seat_id AND od.schedule_Id=:scheduleId)", nativeQuery = true)
    List<String> findSeatAvailable(Long scheduleId);

    @Query(value = "SELECT seat_id FROM seats WHERE seat_name = :seatName", nativeQuery = true)
    Long findSeatIdByName(String seatName);
}
