package org.example.app.service;

import jakarta.ws.rs.core.Response;

public interface AppService<T> {
    Response create(T obj);
    Response fetchAll();
    Response fetchById(Long id);
    Response update(Long id, T obj);
    Response delete(Long id);
}