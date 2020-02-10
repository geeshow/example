package com.ken207.example2.controller;

import com.ken207.example2.domain.Board;
import com.ken207.example2.domain.Comments;
import com.ken207.example2.dto.BoardReqDto;
import com.ken207.example2.dto.BoardResDto;
import com.ken207.example2.dto.CommentReqDto;
import com.ken207.example2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @PostMapping("/hello")
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
        URI redirUri = linkTo(BoardController.class).slash("hello").slash("1").toUri();
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
        Board board = boardService.getBoard(boardId);

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
        Comments comment = boardService.writeComment(commentReqDto);
        return comment.getId();
    }
}
