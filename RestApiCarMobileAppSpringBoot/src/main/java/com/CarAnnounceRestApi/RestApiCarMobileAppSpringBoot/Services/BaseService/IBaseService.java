package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BaseService;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;

import java.io.IOException;
import java.util.List;

public interface IBaseService<T>{
    List<T> getAll() throws IOException;
    T findById(int id);
    void add(T entity);
    void update(T entity);
    void delete(int id);
}
