package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.UserQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
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
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Integer create(String username, String email, String password, String phone, int cityId) {
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
    }

    @Override
    public User findEmailAndPassword(String email, String password)  {
            return jdbcTemplate.queryForObject(UserQuery.SQL_USER_FIND_EMAIL, userRowMapper, email);
    }

    @Override
    public Long getCountByEmail(String email) {
            return jdbcTemplate.queryForObject(UserQuery.SQL_USER_FIND_EMAIL_COUNT, Long.class, email);
    }

    @Override
    public User findById(Integer Id) {
            List<User> users = jdbcTemplate.query(UserQuery.SQL_USER_FIND_BY_ID, new BeanPropertyRowMapper<User>(User.class), Id);
            return DataAccessUtils.uniqueResult(users);
    }

    @Override
    public int findEmailAndChangePassword(String email, String password) {
        return jdbcTemplate.update(UserQuery.SQL_FIND_EMAIL_AND_CHANGE_PASSWORD, password, email);
    }

    private final RowMapper<User> userRowMapper = (((resultSet, i) -> {
        return new User(resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("phone"),
                resultSet.getInt("city_id"));
    }));
}
