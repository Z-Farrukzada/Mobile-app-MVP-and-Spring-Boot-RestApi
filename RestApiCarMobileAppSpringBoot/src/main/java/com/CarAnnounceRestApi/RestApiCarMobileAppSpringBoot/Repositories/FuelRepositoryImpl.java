package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.FuelQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarFuelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class FuelRepositoryImpl implements  FuelRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CarFuelType> getAll() {
            return jdbcTemplate.query(FuelQuery.SQL_ALL_LIST_FUELS,carFuelTypeRowMapper);
    }

    @Override
    public CarFuelType findById(int id){
            return jdbcTemplate.queryForObject(FuelQuery.SQL_FIND_By_ID,carFuelTypeRowMapper,id);
    }

    @Override
    public void add(CarFuelType entity) {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(FuelQuery.SQL_CREATED_FUEL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,entity.getName());
                return ps;
            });
    }

    @Override
    public void update(CarFuelType entity) {
            jdbcTemplate.update(FuelQuery.SQL_UPDATED_FUEL,entity.getName(),entity.getId());
    }

    @Override
    public void delete(int id){
            jdbcTemplate.update(FuelQuery.SQL_DELETED_FUEL,id);

    }

    private final RowMapper<CarFuelType> carFuelTypeRowMapper = ((resultSet, i) -> {
        return  new CarFuelType(resultSet.getInt("id"),
                resultSet.getString("name"));
    });
}
