package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Comment;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;


    @GetMapping
    public ResponseEntity<List<Comment>> getAllListComment(){
        return new ResponseEntity<>(commentService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> findByIdComment(@PathVariable("commentId") int commentId){
        return new ResponseEntity<>(commentService.findById(commentId),HttpStatus.OK);
    }
    @PostMapping("/created")
    public ResponseEntity<Map<String,String>> createdNewComment(@RequestBody Comment comment){
           commentService.add(comment);
           Map<String,String> map = new HashMap<>();
           map.put("New comment","Created");
           return  new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>> updateComment(@RequestBody Comment comment){
        Map<String,String> map = new HashMap<>();
        boolean isExist = commentService.findUserAndAnnouncement(comment);
        if(isExist){
            commentService.update(comment);
            map.put("Comment","Updated");
        }else{
            map.put("Comment","This ad is not yours");
        }
        return  new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Map<String,String>> deletedComment(@PathVariable("commentId") int commentId){
             Map<String,String> map = new HashMap<>();
             commentService.delete(commentId);
             map.put("Comment","Deleted");
        return  new ResponseEntity<>(map,HttpStatus.OK);
    }





}
