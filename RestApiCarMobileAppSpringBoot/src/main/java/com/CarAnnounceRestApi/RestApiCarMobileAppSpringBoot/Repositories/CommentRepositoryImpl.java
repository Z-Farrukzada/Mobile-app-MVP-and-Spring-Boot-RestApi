package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.CommentQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;




@Repository
public class CommentRepositoryImpl implements  CommentRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Comment> getAll() {
            return jdbcTemplate.query(CommentQuery.SQL_ALL_COMMENT_LIST,commentRowMapper);
    }

    @Override
    public Comment findById(int id)  {
            return  jdbcTemplate.queryForObject(CommentQuery.SQL_FIND_BY_ID,commentRowMapper,id);
    }

    @Override
    public void add(Comment entity){
             jdbcTemplate.update(connection -> {
                 PreparedStatement ps = connection.prepareStatement(CommentQuery.SQL_CREATED_COMMENT, Statement.RETURN_GENERATED_KEYS);
                 ps.setString(1,entity.getComment());
                 ps.setDate(2,entity.getWritetime());
                 ps.setInt(3,entity.getUserId());
                 ps.setInt(4,entity.getAnnouncementId());
                 return ps;
             });
    }

    @Override
    public void update(Comment entity){
             jdbcTemplate.update(CommentQuery.SQL_UPDATED_COMMENT,entity.getComment(),entity.getWritetime(),entity.getId());
    }

    @Override
    public void delete(int id) {
             jdbcTemplate.update(CommentQuery.SQL_DELETED_COMMENT,id);
    }

    @Override
    public boolean isFindUserAndAnnounce(Comment comment) {
        List<Comment> query = jdbcTemplate.query(CommentQuery.SQL_FIND_USER_AND_ANNOUNCEMENT,commentRowMapper,comment.getId(),
                comment.getUserId(),comment.getAnnouncementId());
        Comment dbComment = DataAccessUtils.uniqueResult(query);
        return dbComment != null;
    }


    private final RowMapper<Comment> commentRowMapper = ((resultSet, i) -> {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setWritetime(resultSet.getDate("writetime"));
                comment.setUserId(resultSet.getInt("userId"));
                comment.setAnnouncementId(resultSet.getInt("announcementId"));
             return  comment;
    });



}
