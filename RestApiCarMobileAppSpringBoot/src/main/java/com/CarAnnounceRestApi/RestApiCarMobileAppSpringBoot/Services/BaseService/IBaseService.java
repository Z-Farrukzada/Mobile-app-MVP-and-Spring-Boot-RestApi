package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BaseService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IBaseService<T>{
    List<T> getAll() throws IOException;
    T findById(int id);
    Map<String,String> add(T entity);
    Map<String,String> update(T entity);
    Map<String,String> delete(int id);
}
