package com.ken207.example2.service;

import com.ken207.example2.domain.Board;
import com.ken207.example2.domain.Comments;
import com.ken207.example2.dto.BoardReqDto;
import com.ken207.example2.dto.CommentReqDto;
import com.ken207.example2.exception.BizRuntimeException;
import com.ken207.example2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public Long postBoard(BoardReqDto boardReqDto) {
        Board reqBoard = Board.postBoard(boardReqDto.getAuthor()
                , boardReqDto.getSubject()
                , boardReqDto.getContent()
                , boardReqDto.getPassword());

        Board result = boardRepository.save(reqBoard);
        return result.getId();
    }

    @Transactional
    public Board modify(Long boardId, BoardReqDto boardReqDto) {
        Board board = getBoard(boardId);

        board.adjust(boardReqDto.getSubject(), boardReqDto.getContent(), boardReqDto.getPassword());

        return board;
    }

    public Board readBoard(Long boardId) {
        Board board = getBoard(boardId);
        board.read();

        return board;
    }

    public Board getBoard(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if ( !optionalBoard.isPresent() ) {
            throw new BizRuntimeException("해당 개시글이 존재하지 않습니다.");
        }

        return optionalBoard.get();
    }

    public Comments writeComment(CommentReqDto commentReqDto) {
        Board board = this.getBoard(commentReqDto.getBoardId());
        Comments comment = board.writeComment(commentReqDto.getContent());

        return comment;
    }
}
