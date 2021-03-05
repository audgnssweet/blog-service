package com.cos.blog.service.impl;

import com.cos.blog.model.dto.request.ReplySaveRequestDto;
import com.cos.blog.model.entity.Board;
import com.cos.blog.model.entity.Reply;
import com.cos.blog.model.entity.User;
import com.cos.blog.model.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    //이렇게 객체를 일일이 찾는 것이 나쁜 방법은 아니지만(validation check)
    //체크하지않고 빠르게 넣고싶으면 JpaRepository의 Native Query를 이용하는 것이 좋다.
    //native query의 insert는 int만 return 가능.
    @Transactional
    public String saveReply(ReplySaveRequestDto replySaveRequestDto, User user, Board board) {
        Reply reply = new Reply(replySaveRequestDto, user, board);
        replyRepository.save(reply);
        return "정상적으로 댓글이 저장되었습니다";
    }

    public String deleteById(Integer replyId) {
        replyRepository.deleteById(replyId);
        return "댓글이 정상적으로 삭제되었습니다";
    }
}
