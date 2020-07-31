package ru.sber.hack.service.user;

import ru.sber.hack.domain.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity userEntity);

    UserEntity getUserById(long userId);

    List<UserEntity> getAllUsers();
}
