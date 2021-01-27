package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;


import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentQuery {

    public  String SQL_ALL_COMMENT_LIST = "SELECT * FROM comments";
    public  String SQL_FIND_BY_ID = "SELECT * FROM comments WHERE id = ?";
    public  String SQL_CREATED_COMMENT = "INSERT INTO comments(comment,writetime,userId,announcementId) VALUES(?,?,?,?)";
    public  String SQL_FIND_USER_AND_ANNOUNCEMENT = "SELECT * FROM comments WHERE id=? AND userId = ? AND announcementId = ?";
    public  String SQL_UPDATED_COMMENT="UPDATE comments SET comment = ? , writetime = ? WHERE id = ?";
    public  String SQL_DELETED_COMMENT = "DELETE FROM comments WHERE id = ?";

}
