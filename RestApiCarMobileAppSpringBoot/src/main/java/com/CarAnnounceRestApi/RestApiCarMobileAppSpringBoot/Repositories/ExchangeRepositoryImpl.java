package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;


import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.ExchangeQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Exchanges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ExchangeRepositoryImpl implements  ExchangesRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Exchanges> getAll(){
           return jdbcTemplate.query(ExchangeQuery.SQL_ALL_LIST_EXCHANGES,exchangesRowMapper);
    }

    @Override
    public Exchanges findById(int id) {
            return jdbcTemplate.queryForObject(ExchangeQuery.SQL_FIND_By_ID,exchangesRowMapper,id);
    }

    @Override
    public void add(Exchanges entity){
               jdbcTemplate.update(connection -> {
                   PreparedStatement ps = connection.prepareStatement(ExchangeQuery.SQL_CREATED_EXCHANGE, Statement.RETURN_GENERATED_KEYS);
                   ps.setString(1,entity.getName());
                   return ps;
               });
    }

    @Override
    public void update(Exchanges entity) {
            jdbcTemplate.update(ExchangeQuery.SQL_UPDATED_EXCHANGE,entity.getName(),entity.getId());
    }

    @Override
    public void delete(int id) {
              jdbcTemplate.update(ExchangeQuery.SQL_DELETED_EXCHANGE,id);
    }

    private final RowMapper<Exchanges> exchangesRowMapper = ((resultSet, i) -> {
       return  new Exchanges(resultSet.getInt("id"),
                             resultSet.getString("name"));
    });
}
