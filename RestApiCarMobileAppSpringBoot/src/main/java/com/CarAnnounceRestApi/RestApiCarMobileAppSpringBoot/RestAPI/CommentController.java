package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.CommentDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Comment;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CommentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @SneakyThrows
    @GetMapping
    @Cacheable(cacheNames = "AllCommentCache")
    public List<CommentDTO> getAllListComment() {
        return commentService.getAll();
    }

    @GetMapping("/{commentId}")
    @Cacheable(cacheNames = "CommentCache")
    public CommentDTO findByIdComment(@PathVariable("commentId") int commentId){
        return commentService.findById(commentId);
    }

    @PostMapping("/created")
    @Caching(
            put= {@CachePut(cacheNames = "CommentCache", key = "#commentDTO.id")},
            evict = {@CacheEvict(cacheNames = "AllCommentCache",allEntries = true)}
    )
    public Map<String,String> createdNewComment(@RequestBody CommentDTO commentDTO){
           return commentService.add(commentDTO);
    }

    @PutMapping("/updated")
    @Caching(
            put= {@CachePut(cacheNames = "CommentCache", key = "#commentDTO.id")},
            evict = {@CacheEvict(cacheNames = "AllCommentCache",allEntries = true)}
    )
    public Map<String,String> updateComment(@RequestBody CommentDTO commentDTO){
        return  commentService.findUserAndAnnouncement(commentDTO);
    }

    @DeleteMapping("/{commentId}")
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "CommentCache",key = "#id"),
                    @CacheEvict(cacheNames = "AllCommentCache",allEntries = true)
            }
    )
    public Map<String,String> deletedComment(@PathVariable("commentId") int commentId){
        return  commentService.delete(commentId);
    }

}
