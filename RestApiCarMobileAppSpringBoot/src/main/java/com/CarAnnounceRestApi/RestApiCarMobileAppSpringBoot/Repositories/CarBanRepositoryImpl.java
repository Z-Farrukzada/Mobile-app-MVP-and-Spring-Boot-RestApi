package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CarBanRepository;
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

    private  final static  String  SQL_ALL_LIST_CAR_BANS= "SELECT * FROM ban";
    private  final static  String SQL_FIND_BY_ID_BAN = "SELECT * FROM ban WHERE id = ?";
    private  final static  String SQL_CREATED_BAN = "INSERT INTO ban(name) VALUES(?)";
    private  final static  String SQL_UPDATED_BAN="UPDATE ban SET name = ? WHERE id = ?";
    private  final static  String SQL_DELETED_BAN = "DELETE FROM ban WHERE id = ?";

    @Override
    public List<CarBan> getAll() throws CustomNotFoundException {
        try {
            return jdbcTemplate.query(SQL_ALL_LIST_CAR_BANS,carBanRowMapper);
        }catch (Exception e){
            throw  new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public CarBan findById(int id) throws CustomNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID_BAN,carBanRowMapper,id);
        }catch (Exception e){
            throw new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public void add(CarBan entity) throws CustomBadRequest {
         try{
             jdbcTemplate.update(connection -> {
                 PreparedStatement ps = connection.prepareStatement(SQL_CREATED_BAN, Statement.RETURN_GENERATED_KEYS);
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
               jdbcTemplate.update(SQL_UPDATED_BAN,entity.getName(),entity.getId());
           }catch (Exception e){
               throw new CustomBadRequest(e.getLocalizedMessage());
           }
    }

    @Override
    public void delete(int id) throws CustomNotFoundException {
            try {
                jdbcTemplate.update(SQL_DELETED_BAN,id);
            }catch (Exception e){
                throw  new CustomBadRequest(e.getLocalizedMessage());
            }
    }
    public RowMapper<CarBan> carBanRowMapper = (((resultSet, i) -> {
        return  new CarBan(resultSet.getInt("id"),
                resultSet.getString("name"));
    }));

}
