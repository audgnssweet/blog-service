package com.cos.blog.controller.viewcontroller;

import com.cos.blog.service.impl.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebViewController {

    private final BoardService boardService;

    public WebViewController(BoardService boardService) {
        this.boardService = boardService;
    }

    //Controller단에서 Spring Security의 session에 접근하기 위함.
    @GetMapping("/")
    public String getMainPage(
        @PageableDefault(size = 4, sort = "id", direction = Direction.DESC) Pageable pageable,
        ModelMap modelMap
    ) {
        modelMap.addAttribute("boards",
            boardService.getBoards(pageable));
        return "index";
    }

}
