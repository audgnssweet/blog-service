//package com.cos.blog.controller.testcontroller;
//
//import com.cos.blog.model.entity.Board;
//import com.cos.blog.model.entity.Reply;
//import com.cos.blog.model.repository.BoardRepository;
//import com.cos.blog.model.repository.ReplyRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class InfinityTestController {
//
//    private final BoardRepository boardRepository;
//
//    private final ReplyRepository replyRepository;
//
//    //board가 가진 replies eager전략때문에 무한참조가되어버림
//    @GetMapping("/test/board/{id}")
//    public Board getBoards(@PathVariable Integer id) {
//        return boardRepository.findById(id).get();
//    }
//
//    @GetMapping("/test/reply/{id}")
//    public Reply getReply(@PathVariable Integer id) {
//        return replyRepository.findById(id).get();
//    }
//}
