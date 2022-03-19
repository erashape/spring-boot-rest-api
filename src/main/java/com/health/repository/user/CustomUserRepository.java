package com.health.repository.user;

import com.health.domain.user.User;

public interface CustomUserRepository {
    User findByName(long id, String name);
}
