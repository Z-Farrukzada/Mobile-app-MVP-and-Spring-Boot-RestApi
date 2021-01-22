package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BaseRepository;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;

import java.util.List;

public interface IBaseRepository<T>{
    List<T> getAll() throws CustomNotFoundException;
    T findById(int id) throws CustomNotFoundException;
    void add(T entity) throws CustomBadRequest;
    void update(T entity) throws CustomBadRequest;
    void delete(int id) throws  CustomNotFoundException;
}
