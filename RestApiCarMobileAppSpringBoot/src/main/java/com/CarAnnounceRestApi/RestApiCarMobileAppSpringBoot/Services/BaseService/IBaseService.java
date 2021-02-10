package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BaseService;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IBaseService<T>{
    List<T> getAll() throws IOException;
    T findById(int id);
    Map<String,String> add(T entity) throws CustomBadRequest;
    Map<String,String> update(T entity) throws CustomBadRequest;
    Map<String,String> delete(int id) throws CustomBadRequest;
}
