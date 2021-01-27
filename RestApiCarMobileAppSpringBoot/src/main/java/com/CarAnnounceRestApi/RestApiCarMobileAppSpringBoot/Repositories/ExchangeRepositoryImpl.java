package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;


import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.ExchangeQuery;
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
public class ExchangeRepositoryImpl implements  ExchangesRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Exchanges> getAll() throws CustomNotFoundException {
        try {
           return jdbcTemplate.query(ExchangeQuery.SQL_ALL_LIST_EXCHANGES,exchangesRowMapper);
        }catch (Exception e){
            throw  new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public Exchanges findById(int id) throws CustomNotFoundException {
        try {
            return jdbcTemplate.queryForObject(ExchangeQuery.SQL_FIND_By_ID,exchangesRowMapper,id);
        }catch (Exception e){
            throw  new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public void add(Exchanges entity) throws CustomBadRequest {
           try{
               jdbcTemplate.update(connection -> {
                   PreparedStatement ps = connection.prepareStatement(ExchangeQuery.SQL_CREATED_EXCHANGE, Statement.RETURN_GENERATED_KEYS);
                   ps.setString(1,entity.getName());
                   return ps;
               });
           }catch (Exception e){
               throw  new CustomBadRequest(e.getLocalizedMessage());
           }
    }

    @Override
    public void update(Exchanges entity) throws CustomBadRequest {
        try{
            jdbcTemplate.update(ExchangeQuery.SQL_UPDATED_EXCHANGE,entity.getName(),entity.getId());
        }catch (Exception e){
            throw  new CustomBadRequest(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(int id) throws CustomNotFoundException {
          try{
              jdbcTemplate.update(ExchangeQuery.SQL_DELETED_EXCHANGE,id);
          }catch (Exception e){
              throw  new CustomNotFoundException(e.getLocalizedMessage());
          }
    }
    private final RowMapper<Exchanges> exchangesRowMapper = ((resultSet, i) -> {
       return  new Exchanges(resultSet.getInt("id"),
                             resultSet.getString("name"));
    });
}
