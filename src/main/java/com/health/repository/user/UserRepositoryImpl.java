package com.health.repository.user;

import com.health.domain.user.QUser;
import com.health.domain.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;

@Slf4j
public class UserRepositoryImpl implements CustomUserRepository {
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public User findByName(long id, String name) {
        QUser user = QUser.user;

        return queryFactory.selectFrom(user)
                .where(user.id.eq(id))
                .where(user.name.eq(name))
                .fetchOne();
    }
}
