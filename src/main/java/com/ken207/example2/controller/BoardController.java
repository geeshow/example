package com.ken207.example2.controller;

import com.ken207.example2.domain.Board;
import com.ken207.example2.domain.Comments;
import com.ken207.example2.dto.BoardReqDto;
import com.ken207.example2.dto.BoardResDto;
import com.ken207.example2.dto.BoardSearchDto;
import com.ken207.example2.dto.CommentReqDto;
import com.ken207.example2.enums.SearchType;
import com.ken207.example2.repository.BoardRepository;
import com.ken207.example2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @PostMapping
    public ResponseEntity postBoard(@RequestBody BoardReqDto boardReqDto) throws URISyntaxException {

        Long boardId = boardService.postBoard(boardReqDto);
        Board board = boardService.getBoard(boardId);

        BoardResDto result = BoardResDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .subject(board.getSubject())
                .content(board.getContent())
                .hitCount(board.getHitCount())
                .createdTime(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        //URI uri = new URI("/hello/1");
        URI redirUri = linkTo(BoardController.class).slash("hello").slash(board.getId()).toUri();
        return ResponseEntity.created(redirUri).body(result);
    }

    @PutMapping("/update")
    public String adjustBoard() {
        return "";
    }

    @PutMapping("/delete")
    public String deleteBoard() {
        return "";
    }

    @GetMapping("/{boardId}")
    public BoardResDto readBoard(@PathVariable Long boardId) {
        Board board = boardService.readBoard(boardId);

        return BoardResDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .content(board.getContent())
                .createdTime(board.getCreatedTime())
                .hitCount(board.getHitCount())
                .modifiedDate(board.getModifiedDate())
                .subject(board.getSubject())
                .build();
    }

    @PostMapping("/{boardId}")
    public Long writeComment(@PathVariable Long boardId, CommentReqDto commentReqDto) {
        Comments comment = boardService.writeComment(boardId, commentReqDto);
        return comment.getId();
    }

    @GetMapping("/search")
    public List<BoardResDto> search(@ModelAttribute BoardSearchDto boardSearchDto) {
        List<Board> boardList = null;

        switch ( boardSearchDto.getSearchType() ) {
            case AUTHOR :
                boardList = boardRepository.findByAuthorLike(boardSearchDto.getSearchStr());
                break;
            case TITLE :
                boardList = boardRepository.findByTitleLike(boardSearchDto.getSearchStr());
                break;
            case CONTENT :
                boardList = boardRepository.findByContentLike(boardSearchDto.getSearchStr());
                break;
        }


        return boardList.stream().map(board -> BoardResDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .content(board.getContent())
                    .createdTime(board.getCreatedTime())
                    .hitCount(board.getHitCount())
                    .modifiedDate(board.getModifiedDate())
                    .subject(board.getSubject())
                    .build())
                .collect(Collectors.toList());
    }
}
