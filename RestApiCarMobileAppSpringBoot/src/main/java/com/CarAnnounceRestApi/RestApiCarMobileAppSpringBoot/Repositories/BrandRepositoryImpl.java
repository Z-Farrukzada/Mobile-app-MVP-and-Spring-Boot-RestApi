package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.BrandQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrandWithModelCount;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


@Repository
public class BrandRepositoryImpl implements BrandRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CarBrand> getAll() {
        return jdbcTemplate.query(BrandQuery.SQL_ALL_BRAND,carBrandRowMapper);
    }

    @Override
    public CarBrand findById(int brandId) {
            return jdbcTemplate.queryForObject(BrandQuery.SQL_FIND_BY_ID_BRAND,carBrandRowMapper,brandId);
    }

    @Override
    public void add(CarBrand carBrand) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(BrandQuery.SQL_CREATE_NEW_BRAND, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,carBrand.getName());
            return ps;
        });

    }
    @Override
    public void update(CarBrand carBrand) {
            jdbcTemplate.update(BrandQuery.SQL_UPDATED_BRAND,carBrand.getName(),carBrand.getId());
    }
    @Override
    public void delete(int brandId) {
            jdbcTemplate.update(BrandQuery.SQL_DELETED_BRAND,brandId);
    }

    @Override
    public List<CarBrandWithModelCount> popularBrandList() {
            return jdbcTemplate.query(BrandQuery.SQL_BRAND_POPULAR_LIST,carBrandWithModelCountRowMapper);
    }

    public RowMapper<CarBrand> carBrandRowMapper = (((resultSet, i) -> {
        return  new CarBrand(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("logoImage"));
    }));

    public RowMapper<CarBrandWithModelCount> carBrandWithModelCountRowMapper = (((resultSet, i) -> {
        return  new CarBrandWithModelCount(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("logoImage"),
                resultSet.getInt("count"));
    }));



}
