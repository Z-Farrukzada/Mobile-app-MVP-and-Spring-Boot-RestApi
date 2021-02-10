package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.CommentDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Comment;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CommentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<CommentDTO>> getAllListComment() {
        return new ResponseEntity<>(commentService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> findByIdComment(@PathVariable("commentId") int commentId){
        return new ResponseEntity<>(commentService.findById(commentId),HttpStatus.OK);
    }

    @PostMapping("/created")
    public ResponseEntity<Map<String,String>> createdNewComment(@RequestBody CommentDTO commentDTO){
           return  new ResponseEntity<>(commentService.add(commentDTO),HttpStatus.CREATED);
    }

    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>> updateComment(@RequestBody CommentDTO commentDTO){
        return  new ResponseEntity<>(commentService.findUserAndAnnouncement(commentDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Map<String,String>> deletedComment(@PathVariable("commentId") int commentId){
        return  new ResponseEntity<>(commentService.delete(commentId),HttpStatus.OK);
    }

}
