package com.healthapi.repository.manager;

import com.healthapi.entity.manager.ManagerEntity;

public interface CustomManagerRepository {
    ManagerEntity findByName(long id, String name);
}
