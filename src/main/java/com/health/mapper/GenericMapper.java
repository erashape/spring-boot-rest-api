package com.health.mapper;

public interface GenericMapper<D, E> {
    D toDto(E e);
    E toEntity(D d);
}
