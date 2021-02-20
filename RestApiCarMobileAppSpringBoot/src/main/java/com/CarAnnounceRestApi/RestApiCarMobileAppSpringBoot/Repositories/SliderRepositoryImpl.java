package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.SlideDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.SliderQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SliderRepositoryImpl implements  SliderRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CarSlide> getAll() {
        return jdbcTemplate.query(SliderQuery.SQL_GET_ALL_LIST_SLIDER_IMAGE,slideDTORowMapper);
    }

    @Override
    public CarSlide findById(int id) {
        return null;
    }

    @Override
    public void add(CarSlide entity) {

    }

    @Override
    public void update(CarSlide entity) {

    }

    @Override
    public void delete(int id) {

    }

    private final RowMapper<CarSlide> slideDTORowMapper = (((resultSet, i) -> {
              return new CarSlide(resultSet.getInt("id"),
                                    resultSet.getString("imageUrl"));
    }));
}
