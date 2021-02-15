package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.CommentDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Comment;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class CommentServiceImpl  implements  CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<CommentDTO> getAll() {
        log.debug("Butun şərhlər axtarilir...");
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
        log.debug("Butun şərhlər " + commentDto);
        return commentDto;
    }

    @Override
        public CommentDTO findById(int id) {
        log.debug("Şərh id-ye gore axtarilir...");
        Comment comment = commentRepository.findById(id);
        log.debug("Şərh id-ye gore " + comment);
        return CommentDTO.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .writetime(comment.getWritetime())
                .userId(comment.getUserId())
                .announcementId(comment.getAnnouncementId())
                .build();
    }

    @Override
    public Map<String,String> add(CommentDTO entity) {
            log.debug("Şərh elave olunur...");
        Map<String,String> map = new HashMap<>();
            commentRepository.add(addComment(entity));
            map.put("Message","Şərh əlavə olundu");
            log.debug("Şərh elave olundu");
        return map;
    }

    @Override
    public Map<String,String> update(CommentDTO entity) {
        log.debug("Şərh deyisdirilir...");
        Map<String,String> map = new HashMap<>();
            commentRepository.update(addComment(entity));
            map.put("Message","Şərh deyisdirildi");
        log.debug("Şərh deyisdirildi");
        return map;
    }

    @Override
    public  Map<String,String> delete(int id) {
        log.debug("Şərh silinir...");
        Map<String,String> map = new HashMap<>();
            commentRepository.delete(id);
            map.put("Message","Şərh silindi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public Map<String,String> findUserAndAnnouncement(CommentDTO commentDto){
        log.debug("Istifadeci serhi deyisdirir...");
        Map<String,String> map = new HashMap<>();
          boolean isExist = commentRepository.isFindUserAndAnnounce(addComment(commentDto));
        if(isExist){
            commentRepository.update(addComment(commentDto));
            map.put("Message","Istifadeci Serhi deyisdirildi");
        }else{
            map.put("Message", "Istifadeci Serhi deyisdirilmedi");
        }
        log.debug(map.get("Message"));
        return map;
    }

    public Comment addComment(CommentDTO commentDTO){
        log.debug("CommentDTO Comment-e elave olunur...");
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
        log.debug("CommentDTO Comment-e elave olundu " +comment);
        return comment;
    }

}
