package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.FuelQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarFuelType;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Exchanges;
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
public class FuelRepositoryImpl implements  FuelRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CarFuelType> getAll() throws CustomNotFoundException {
        try {
            return jdbcTemplate.query(FuelQuery.SQL_ALL_LIST_FUELS,carFuelTypeRowMapper);
        }catch (Exception e){
            throw  new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public CarFuelType findById(int id) throws CustomNotFoundException {
        try {
            return jdbcTemplate.queryForObject(FuelQuery.SQL_FIND_By_ID,carFuelTypeRowMapper,id);
        }catch (Exception e){
            throw  new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public void add(CarFuelType entity) throws CustomBadRequest {
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(FuelQuery.SQL_CREATED_FUEL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,entity.getName());
                return ps;
            });
        }catch (Exception e){
            throw  new CustomBadRequest(e.getLocalizedMessage());
        }
    }

    @Override
    public void update(CarFuelType entity) throws CustomBadRequest {
        try{
            jdbcTemplate.update(FuelQuery.SQL_UPDATED_FUEL,entity.getName(),entity.getId());
        }catch (Exception e){
            throw  new CustomBadRequest(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(int id) throws CustomNotFoundException {
        try{
            jdbcTemplate.update(FuelQuery.SQL_DELETED_FUEL,id);
        }catch (Exception e){
            throw  new CustomNotFoundException(e.getLocalizedMessage());
        }
    }
    private final RowMapper<CarFuelType> carFuelTypeRowMapper = ((resultSet, i) -> {
        return  new CarFuelType(resultSet.getInt("id"),
                resultSet.getString("name"));
    });
}
