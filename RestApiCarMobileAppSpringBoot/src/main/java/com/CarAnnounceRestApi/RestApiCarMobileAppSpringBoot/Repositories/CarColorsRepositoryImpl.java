package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.CarColorsQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CarColorsRepositoryImpl implements CarColorsRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CarColors> getAll() {
        return  jdbcTemplate.query(CarColorsQuery.SQL_ALL_COLORS_LIST,carColorsRowMapper);
    }

    @Override
    public CarColors findById(int id) {
        return  jdbcTemplate.queryForObject(CarColorsQuery.SQL_FIND_BY_ID_COLOR,carColorsRowMapper,id);
    }

    @Override
    public void add(CarColors entity) {
          jdbcTemplate.update(connection -> {
              PreparedStatement ps = connection.prepareStatement(CarColorsQuery.SQL_CREATED_COLOR, Statement.RETURN_GENERATED_KEYS);
              ps.setString(1,entity.getName());
              return ps;
          });
    }

    @Override
    public void update(CarColors entity){
            jdbcTemplate.update(CarColorsQuery.SQL_UPDATED_COLOR,entity.getName(),entity.getId());
    }

    @Override
    public void delete(int id){
              jdbcTemplate.update(CarColorsQuery.SQL_DELETED_COLOR,id);
    }

    public RowMapper<CarColors> carColorsRowMapper = (((resultSet, i) -> {
        return  new CarColors(resultSet.getInt("id"),
                resultSet.getString("name"));
    }));
}
