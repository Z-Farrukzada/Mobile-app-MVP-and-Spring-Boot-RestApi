package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Comment;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl  implements  CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    @Override
    public Comment findById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public void add(Comment entity) { commentRepository.add(entity); }

    @Override
    public void update(Comment entity)
    {
        commentRepository.update(entity);
    }

    @Override
    public void delete(int id) { commentRepository.delete(id); }

    @Override
    public boolean findUserAndAnnouncement(Comment comment) {
        return commentRepository.isFindUserAndAnnounce(comment);
    }

}
