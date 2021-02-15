package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.DetailQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarDetail;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class DetailRepositoryImpl implements  DetailsRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CarDetail> getAll()  {
            return jdbcTemplate.query(DetailQuery.SQL_DETAILS_LIST,carDetailRowMapper);
    }

    @Override
    public CarDetail findById(int id)  {
            return jdbcTemplate.queryForObject(DetailQuery.SQL_FIND_By_ID,carDetailRowMapper,id);
    }

    @Override
    public void add(CarDetail entity)  {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(DetailQuery.SQL_CREATED_DETAIL, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, entity.isHatch());
            ps.setBoolean(2, entity.isABS());
            ps.setBoolean(3, entity.isAircondition());
            return ps;
        });
    }

    @Override
    public void update(CarDetail entity)  {
            jdbcTemplate.update(DetailQuery.SQL_UPDATED_DETAIL,entity.isHatch(),entity.isABS(),entity.isAircondition(),entity.getId());
    }

    @Override
    public void delete(int id)  {
            jdbcTemplate.update(DetailQuery.SQL_DELETED_DETAIL,id);
    }

    private final RowMapper<CarDetail> carDetailRowMapper = ((resultSet, i) -> {
        return  new CarDetail(resultSet.getInt("id"),
                resultSet.getBoolean("hatch"),
                resultSet.getBoolean("ABS"),
                resultSet.getBoolean("aircondition"));
    });
}
