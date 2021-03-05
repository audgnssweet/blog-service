package com.cos.blog.controller.apicontroller;

import com.cos.blog.common.Response;
import com.cos.blog.model.dto.request.BoardSaveRequestDto;
import com.cos.blog.model.entity.User;
import com.cos.blog.service.impl.BoardService;
import com.cos.blog.service.impl.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final UserService userService;

    private final BoardService boardService;

    @PostMapping("/board")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<String> postBoard(
        @Valid @RequestBody BoardSaveRequestDto boardSaveRequestDto,
        BindingResult bindingResult
    ) {
        final User foundUser = userService.getUserDetail(boardSaveRequestDto.getUserId());
        final String message = boardService.postBoard(boardSaveRequestDto, foundUser);
        return new Response<>(HttpStatus.CREATED, message);
    }

    //board는 replies를 갖기 때문에 그냥 삭제가 불가능함.
    //그래서 entity에 cascade 옵션을 줘야함.
    @DeleteMapping("/board/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<String> deleteBoard(@PathVariable Integer id) {
        final String message = boardService.deleteById(id);
        return new Response<>(HttpStatus.OK, message);
    }

    @PutMapping("/board/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public Response<String> updateBoard(
        @PathVariable Integer boardId,
        @RequestBody BoardSaveRequestDto boardSaveRequestDto
    ) {
        final String message = boardService.updateBoard(boardId, boardSaveRequestDto);
        return new Response<>(HttpStatus.OK, message);
    }

}
