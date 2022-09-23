package com.farhan.bioskopapi.repository;

import com.farhan.bioskopapi.entity.StudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<StudioEntity, Long> {
}