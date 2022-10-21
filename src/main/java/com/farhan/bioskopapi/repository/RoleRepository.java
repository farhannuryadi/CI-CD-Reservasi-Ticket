package com.farhan.bioskopapi.repository;

import com.farhan.bioskopapi.entity.ERole;
import com.farhan.bioskopapi.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(ERole name);
}
