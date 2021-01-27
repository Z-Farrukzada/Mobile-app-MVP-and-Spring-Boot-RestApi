package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.UserQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.User;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomAuthException;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Integer create(String username, String email, String password, String phone, int cityId) throws CustomAuthException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(UserQuery.SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setString(4, phone);
                ps.setInt(5, cityId);
                return ps;
            }, keyHolder);
            return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("ID");
        } catch (Exception e) {
            throw new CustomAuthException("MESSAGE" + e.getLocalizedMessage());
        }
    }

    @Override
    public User findEmailAndPassword(String email, String password) throws CustomAuthException {
        try {
            User user = jdbcTemplate.queryForObject(UserQuery.SQL_USER_FIND_EMAIL, userRowMapper, email);
            assert user != null;
            if (!user.getPassword().equals(password)) {
                throw new CustomAuthException("Invalid email and password");
            }
            return user;
        } catch (Exception e) {
            throw new CustomAuthException("Invalid email and password");
        }
    }

    @Override
    public Long getCountByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(UserQuery.SQL_USER_FIND_EMAIL_COUNT, Long.class, email);

        } catch (Exception e) {
            throw new CustomAuthException(e.getMessage());
        }
    }

    @Override
    public User findById(Integer Id) {
        try {
            List<User> users = jdbcTemplate.query(UserQuery.SQL_USER_FIND_BY_ID, new BeanPropertyRowMapper<User>(User.class), Id);
            return DataAccessUtils.uniqueResult(users);
        } catch (Exception e) {
            throw new CustomAuthException(e.getLocalizedMessage());
        }

    }

    @Override
    public String findEmailAndChangePassword(String email, String password) {
        String message;
        int data = jdbcTemplate.update(UserQuery.SQL_FIND_EMAIL_AND_CHANGE_PASSWORD, password, email);
        if (data > 0) {
            message = "Şifrə dəyişdirildi.";
        } else {
            message = "Səhv email";
        }
        return message;

    }

    public RowMapper<User> userRowMapper = (((resultSet, i) -> {
        return new User(resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("phone"),
                resultSet.getInt("city_id"));
    }));
}
