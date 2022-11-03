package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.StudioEntity;
import com.farhan.bioskopapi.repository.StudioRepository;
import com.farhan.bioskopapi.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;

    @Autowired
    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    public StudioEntity save(StudioEntity studioEntity) {
        return studioRepository.save(studioEntity);
    }

    @Override
    public StudioEntity findOne(Long id) {
        Optional<StudioEntity> studioEntity = studioRepository.findById(id);
        if (studioEntity.isEmpty()) {
            return null;
        }
        return studioEntity.get();
    }

    @Override
    public Iterable<StudioEntity> findAll() {
        return studioRepository.findAll();
    }

    @Override
    public void removeOne(Long id) {
        studioRepository.deleteById(id);
    }
}
