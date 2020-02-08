package com.ken207.example2.controller;

import com.ken207.example2.dto.BoardReqDto;
import com.ken207.example2.dto.BoardResDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @PostMapping("/hello")
    public ResponseEntity postBoard(@RequestBody BoardReqDto boardReqDto) throws URISyntaxException {

        BoardResDto result = BoardResDto.builder()
                .author(boardReqDto.getAuthor())
                .subject(boardReqDto.getSubject())
                .content(boardReqDto.getContent())
                .hitCount(0)
                .delYn(false)
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
}
