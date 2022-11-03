package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.repository.UserRepository;
import com.farhan.bioskopapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findOne(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void removeOne(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        user.ifPresent(userEntity -> userRepository.deleteById(userEntity.getId()));
    }
}
