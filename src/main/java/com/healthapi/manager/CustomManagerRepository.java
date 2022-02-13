package com.healthapi.manager;

public interface CustomManagerRepository {
    ManagerEntity findByName(long id, String name);
}
