package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BanDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.CommentDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Comment;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommentServiceImpl  implements  CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<CommentDTO> getAll() {
        List<CommentDTO> commentDto = new ArrayList<>();
        for (Comment comment : commentRepository.getAll()) {
            commentDto.add(CommentDTO.builder()
                    .id(comment.getId())
                    .comment(comment.getComment())
                    .writetime(comment.getWritetime())
                    .userId(comment.getUserId())
                    .announcementId(comment.getAnnouncementId())
                    .build());
        }
        return commentDto;
    }

    @Override
        public CommentDTO findById(int id) {
        Comment comment = commentRepository.findById(id);
        return CommentDTO.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .writetime(comment.getWritetime())
                .userId(comment.getUserId())
                .announcementId(comment.getAnnouncementId())
                .build();
    }

    @Override
    public Map<String,String> add(CommentDTO entity) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try{
            commentRepository.add(addComment(entity));
            map.put("Message","Şərh əlavə olundu");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Şərh əlavə olunmadi")));
        }
        return map;
    }

    @Override
    public Map<String,String> update(CommentDTO entity) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try{
            commentRepository.update(addComment(entity));
            map.put("Message","Şərh deyisdirildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Şərh deyisdirlmedi !!!")));
        }
        return map;
    }

    @Override
    public  Map<String,String> delete(int id) {
        Map<String,String> map = new HashMap<>();
        try{
            commentRepository.delete(id);
            map.put("Message","Serh silindi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Serh silinmedi !!!")));
        }
        return map;
    }

    @Override
    public Map<String,String> findUserAndAnnouncement(CommentDTO commentDto) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
          boolean isExist = commentRepository.isFindUserAndAnnounce(addComment(commentDto));
        if(isExist){
            commentRepository.update(addComment(commentDto));
            map.put("Message"," Serh deyisdirildi");
        }else{
            map.put("Message", String.valueOf(new CustomBadRequest("Bu serh sizin deyil")));
        }
        return map;
    }

    public Comment addComment(CommentDTO commentDTO){
        Comment comment =null;
        if(commentDTO.getComment() != null
                && commentDTO.getAnnouncementId() != 0 && commentDTO.getUserId() != 0 && commentDTO.getWritetime() != null){
            comment = Comment.builder()
                    .id(commentDTO.getId())
                    .comment(commentDTO.getComment())
                    .writetime(commentDTO.getWritetime())
                    .userId(commentDTO.getUserId())
                    .announcementId(commentDTO.getAnnouncementId())
                    .build();
        }
        return comment;
    }

}
