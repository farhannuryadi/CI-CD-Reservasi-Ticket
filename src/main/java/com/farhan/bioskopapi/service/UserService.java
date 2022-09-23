package com.farhan.bioskopapi.service;

import com.farhan.bioskopapi.entity.UserEntity;

public interface UserService {

    UserEntity save(UserEntity userEntity);
    UserEntity findOne(String username);
    Iterable<UserEntity> findAll();
    void removeOne(String username);
}
