package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.CarColorsQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CarColorsRepositoryImpl implements CarColorsRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CarColors> getAll() throws CustomNotFoundException {
        try {
              return  jdbcTemplate.query(CarColorsQuery.SQL_ALL_COLORS_LIST,carColorsRowMapper);
        }catch (Exception e){
            throw  new CustomNotFoundException("Not found CarColors list");
        }
    }

    @Override
    public CarColors findById(int id) throws CustomNotFoundException {
        try{
             return  jdbcTemplate.queryForObject(CarColorsQuery.SQL_FIND_BY_ID_COLOR,carColorsRowMapper,id);
        }catch (Exception e){
            throw  new CustomNotFoundException("Not found");
        }
    }

    @Override
    public void add(CarColors entity) throws CustomBadRequest {
      try {
          jdbcTemplate.update(connection -> {
              PreparedStatement ps = connection.prepareStatement(CarColorsQuery.SQL_CREATED_COLOR, Statement.RETURN_GENERATED_KEYS);
              ps.setString(1,entity.getName());
              return ps;
          });
      }catch (Exception e){
          throw  new CustomBadRequest("Not created");
      }
    }

    @Override
    public void update(CarColors entity) throws CustomBadRequest {
        try{
            jdbcTemplate.update(CarColorsQuery.SQL_UPDATED_COLOR,entity.getName(),entity.getId());
        }catch (Exception e){
            throw  new CustomBadRequest("Not updated");
        }
    }

    @Override
    public void delete(int id) throws CustomNotFoundException {
         try{
              jdbcTemplate.update(CarColorsQuery.SQL_DELETED_COLOR,id);
         }catch (Exception e){
             throw new CustomBadRequest("Not deleted");
         }
    }
    public RowMapper<CarColors> carColorsRowMapper = (((resultSet, i) -> {
        return  new CarColors(resultSet.getInt("id"),
                resultSet.getString("name"));
    }));
}
