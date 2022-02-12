package com.healthapi.manager;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class ManagerRepositoryImpl implements CustomManagerRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public ManagerRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
}
