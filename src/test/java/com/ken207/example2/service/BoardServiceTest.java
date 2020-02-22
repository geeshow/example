package com.ken207.example2.service;

import com.ken207.example2.domain.Board;
import com.ken207.example2.dto.BoardReqDto;
import com.ken207.example2.repository.BoardRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    public void postBoardOkTest() {
        //given
        BoardReqDto boardReqDto = BoardReqDto.builder()
                .author("저자")
                .content("내용")
                .password("1234")
                .subject("제목")
                .build();

        //when
        Long boardId = boardService.postBoard(boardReqDto);
        Board board = boardService.getBoard(boardId);

        //then
        Assert.assertEquals("제목",board.getSubject());
    }

}