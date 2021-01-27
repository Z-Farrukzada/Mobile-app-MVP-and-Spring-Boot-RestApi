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
    public List<CarBan> getAll() throws CustomNotFoundException {
        try {
            return jdbcTemplate.query(CarBanQuery.SQL_ALL_LIST_CAR_BANS,carBanRowMapper);
        }catch (Exception e){
            throw  new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public CarBan findById(int id) throws CustomNotFoundException {
        try{
            return jdbcTemplate.queryForObject(CarBanQuery.SQL_FIND_BY_ID_BAN,carBanRowMapper,id);
        }catch (Exception e){
            throw new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public void add(CarBan entity) throws CustomBadRequest {
         try{
             jdbcTemplate.update(connection -> {
                 PreparedStatement ps = connection.prepareStatement(CarBanQuery.SQL_CREATED_BAN, Statement.RETURN_GENERATED_KEYS);
                 ps.setString(1,entity.getName());
                 return ps;
             });
         }catch (Exception e){
             throw  new CustomBadRequest(e.getLocalizedMessage());
         }
    }

    @Override
    public void update(CarBan entity) throws CustomBadRequest {
           try{
               jdbcTemplate.update(CarBanQuery.SQL_UPDATED_BAN,entity.getName(),entity.getId());
           }catch (Exception e){
               throw new CustomBadRequest(e.getLocalizedMessage());
           }
    }

    @Override
    public void delete(int id) throws CustomNotFoundException {
            try {
                jdbcTemplate.update(CarBanQuery.SQL_DELETED_BAN,id);
            }catch (Exception e){
                throw  new CustomBadRequest(e.getLocalizedMessage());
            }
    }
    public RowMapper<CarBan> carBanRowMapper = (((resultSet, i) -> {
        return  new CarBan(resultSet.getInt("id"),
                resultSet.getString("name"));
    }));

}
