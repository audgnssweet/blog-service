package com.cos.blog.model.entity;

import com.cos.blog.model.dto.request.ReplySaveRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String content;

    /*
    무한참조 방지하는 방법
    1. Entity로받고 Json직렬화 하기전에 DTO생성 후 복사
    - BeanUtils.copyProperties(A,B)
    2. 처음부터 DTO로 DB에서 받기
    3. @JsonIgnore
    4. @JsonIgnoreProperties
    5. @JsonBackReference, @JsonManagedReference
     */
    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonIgnoreProperties(value = "replies")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDateTime createDate;

    //댓글 작성을 위한 생성자
    public Reply(ReplySaveRequestDto replySaveRequestDto, User user, Board board) {
        this.content = replySaveRequestDto.getContent();
        this.user = user;
        this.board = board;
    }
}
