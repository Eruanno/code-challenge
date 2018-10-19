package com.codechallange.repository.dao;

import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(String id);

    void create(T t);

    void update(T t);
}