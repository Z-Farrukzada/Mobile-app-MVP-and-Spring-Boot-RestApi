package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BaseRepository;

import java.util.List;

public interface IBaseRepository<T>{
    List<T> getAll();
    T findById(int id);
    void add(T entity);
    void update(T entity);
    void delete(int id);
}
