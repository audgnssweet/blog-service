package com.cos.blog.service.impl;

import com.cos.blog.exception.exceptions.BadBoardRequestException;
import com.cos.blog.exception.exceptions.BoardNotFoundException;
import com.cos.blog.model.dto.request.BoardSaveRequestDto;
import com.cos.blog.model.entity.Board;
import com.cos.blog.model.entity.User;
import com.cos.blog.model.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public String postBoard(BoardSaveRequestDto boardSaveRequestDto, User user) {

        Board board = new Board(boardSaveRequestDto);
        board.setViews(0).setUser(user);
        boardRepository.save(board);
        return "글 쓰기를 완료했습니다";
    }

    @Transactional(readOnly = true)
    public Page<Board> getBoards(Pageable pageable) {
        //이렇게하면 page를 넘겨줌.
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board getBoardDetail(Integer boardId) {
        return boardRepository.findById(boardId)
            .orElseThrow(BoardNotFoundException::new);
    }

    @Transactional
    public String deleteById(Integer id) {
        boardRepository.deleteById(id);
        return "정상적으로 삭제되었습니다";
    }

    @Transactional
    public String updateBoard(Integer boardId, BoardSaveRequestDto boardSaveRequestDto) {
        final Board foundBoard = boardRepository.findById(boardId)
            .orElseThrow(BoardNotFoundException::new);
        foundBoard.setTitle(boardSaveRequestDto.getTitle());
        foundBoard.setContent(boardSaveRequestDto.getContent());
        return "게시글이 업데이트 되었습니다";
    }
}
