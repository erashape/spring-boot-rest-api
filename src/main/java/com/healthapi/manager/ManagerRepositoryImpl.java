package com.healthapi.manager;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;

@Slf4j
public class ManagerRepositoryImpl implements CustomManagerRepository {
    private final JPAQueryFactory queryFactory;

    public ManagerRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public ManagerEntity findByName(long id, String name) {
        QManagerEntity manager = QManagerEntity.managerEntity;

        return queryFactory.selectFrom(manager)
                .where(manager.id.eq(id))
                .where(manager.name.eq(name))
                .fetchOne();
    }
}
