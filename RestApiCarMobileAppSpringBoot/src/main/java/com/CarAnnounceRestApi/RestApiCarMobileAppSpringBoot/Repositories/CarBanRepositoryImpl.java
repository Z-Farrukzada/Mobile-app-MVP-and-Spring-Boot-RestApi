package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.CarBanQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
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
public class CarBanRepositoryImpl implements CarBanRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CarBan> getAll(){
        return jdbcTemplate.query(CarBanQuery.SQL_ALL_LIST_CAR_BANS,carBanRowMapper);
    }

    @Override
    public CarBan findById(int id){
        return jdbcTemplate.queryForObject(CarBanQuery.SQL_FIND_BY_ID_BAN,carBanRowMapper,id);
    }

    @Override
    public void add(CarBan entity){
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CarBanQuery.SQL_CREATED_BAN, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,entity.getName());
            return ps;
        });
    }

    @Override
    public void update(CarBan entity){
        jdbcTemplate.update(CarBanQuery.SQL_UPDATED_BAN,entity.getName(),entity.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(CarBanQuery.SQL_DELETED_BAN, id);
    }

    public RowMapper<CarBan> carBanRowMapper = (((resultSet, i) -> {
        return  new CarBan(resultSet.getInt("id"),
                resultSet.getString("name"));
    }));

}
