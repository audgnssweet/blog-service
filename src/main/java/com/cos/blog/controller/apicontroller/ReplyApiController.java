package com.cos.blog.controller.apicontroller;

import com.cos.blog.common.Response;
import com.cos.blog.model.dto.request.ReplySaveRequestDto;
import com.cos.blog.model.entity.Board;
import com.cos.blog.model.entity.User;
import com.cos.blog.service.impl.BoardService;
import com.cos.blog.service.impl.ReplyService;
import com.cos.blog.service.impl.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    private final UserService userService;

    private final BoardService boardService;


    //프로젝트가 커질수록 data를 dto로 주고받는게 좋음. entity를 직접 사용하는 것이 안좋음.
    @PostMapping("/board/{boardId}/reply")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<String> postReply(
        @Valid @RequestBody ReplySaveRequestDto replySaveRequestDto,
        BindingResult bindingResult
    ) {
        //service내에서 다른 service를 호출하는 것보다,
        //한 controller에서 여러 service를 호출하는 것이 재사용성이 좋다.
        final User user = userService.getUserDetail(replySaveRequestDto.getUserId());
        final Board board = boardService.getBoardDetail(replySaveRequestDto.getBoardId());
        final String message = replyService.saveReply(replySaveRequestDto, user, board);
        return new Response<>(HttpStatus.CREATED, message);
    }

    @DeleteMapping("/board/{boardId}/reply/{replyId}")
    @ResponseStatus(HttpStatus.OK)
    public Response<String> deleteReply(@PathVariable Integer replyId) {
        final String message = replyService.deleteById(replyId);
        return new Response<>(HttpStatus.OK, message);
    }
}
