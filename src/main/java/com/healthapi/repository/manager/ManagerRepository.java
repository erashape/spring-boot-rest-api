package com.healthapi.repository.manager;

import com.healthapi.entity.manager.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long>, CustomManagerRepository {
}
