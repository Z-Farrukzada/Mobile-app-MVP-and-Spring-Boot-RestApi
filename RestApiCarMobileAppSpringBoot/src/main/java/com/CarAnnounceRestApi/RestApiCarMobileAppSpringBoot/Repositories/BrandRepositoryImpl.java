package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class BrandRepositoryImpl implements BrandRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    private  final static  String SQL_ALL_BRAND = "SELECT * FROM brand";
    private  final static  String SQL_FIND_BY_ID_BRAND = "SELECT * FROM brand WHERE id = ?";
    private  final static  String SQL_CREATE_NEW_BRAND = "INSERT INTO brand(name) VALUES(?)";
    private  final static  String SQL_UPDATED_BRAND ="UPDATE brand SET name = ? WHERE id = ? ";
    private  final static  String SQL_DELETED_BRAND ="DELETE FROM brand WHERE id = ? ";

    @Override
    public List<CarBrand> getAll() {
        try{
            return jdbcTemplate.query(SQL_ALL_BRAND,carBrandRowMapper);
        }catch (Exception e){
            throw  new CustomNotFoundException("Not Found");
        }
    }
    @Override
    public CarBrand findById(int brandId) {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID_BRAND,carBrandRowMapper,brandId);
        }catch (Exception e){
            throw  new CustomNotFoundException("This brand is not");
        }
    }
    @Override
    public void add(CarBrand carBrand) {
           try{
               jdbcTemplate.update(connection -> {
                   PreparedStatement ps = connection.prepareStatement(SQL_CREATE_NEW_BRAND, Statement.RETURN_GENERATED_KEYS);
                   ps.setString(1,carBrand.getName());
                   return ps;
               });
           }catch (Exception e){
               throw  new CustomBadRequest("Not created");
           }
    }
    @Override
    public void update(CarBrand carBrand) {
        try {
            jdbcTemplate.update(SQL_UPDATED_BRAND,carBrand.getName(),carBrand.getId());
        }catch (Exception e){
            throw new CustomBadRequest("Not updated");
        }

    }
    @Override
    public void delete(int brandId) throws CustomNotFoundException {
          try{
              jdbcTemplate.update(SQL_DELETED_BRAND,brandId);
          }catch (Exception e){
              throw  new CustomBadRequest("Not deleted");
          }
    }

    public RowMapper<CarBrand> carBrandRowMapper = (((resultSet, i) -> {
                      return  new CarBrand(resultSet.getInt("id"),
                                            resultSet.getString("name"));
    }));
}
