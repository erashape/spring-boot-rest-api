package com.healthapi.repository.user;

import com.healthapi.domain.user.User;

public interface CustomUserRepository {
    User findByName(long id, String name);
}
