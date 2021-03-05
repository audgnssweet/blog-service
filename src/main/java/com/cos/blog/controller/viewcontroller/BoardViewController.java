package com.cos.blog.controller.viewcontroller;

import com.cos.blog.model.entity.Board;
import com.cos.blog.service.impl.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardViewController {

    private final BoardService boardService;

    public BoardViewController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/{id}")
    public String getBoardDetail(
        @PathVariable Integer id, ModelMap modelMap
    ) {
        final Board boardDetail = boardService
            .getBoardDetail(id);
        modelMap.addAttribute("boardDetail", boardDetail);
        return "board/boarddetail";
    }

    @GetMapping("/board/writeForm")
    public String getWriteForm() {
        return "/board/writeform";
    }

    @GetMapping("/board/updateForm/{id}")
    public String getUpdateForm(
        @PathVariable Integer id, ModelMap modelMap
    ) {
        modelMap.addAttribute("boardDetail",
            boardService.getBoardDetail(id));
        return "/board/updateform";
    }
}
