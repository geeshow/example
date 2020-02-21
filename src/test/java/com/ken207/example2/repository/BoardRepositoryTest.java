package com.ken207.example2.repository;

import com.ken207.example2.domain.Board;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    private Long boardId;

    @Before
    public void setUp() {
        Board requestData = Board.builder()
                .author("박규태")
                .content("내용")
                .createdTime(LocalDateTime.now())
                .subject("제목")
                .build();

        Board newBoard = boardRepository.save(requestData);
        boardId = newBoard.getId();
    }

    @Test
    public void Board등록정상테스트() {
        //given

        Board requestData = Board.builder()
                .author("박규태")
                .content("내용")
                .createdTime(LocalDateTime.now())
                .subject("제목")
                .build();

        //when
        Board newBoard = boardRepository.save(requestData);

        //then
        assertEquals("박규태", newBoard.getAuthor());
        assertEquals("내용", newBoard.getContent());
        assertEquals("제목", newBoard.getSubject());
        assertNotNull(newBoard.getId());
        assertNotNull(newBoard.getCreatedTime());
    }

    @Test
    public void Board수정테스트() {
        //given
        Board requestData = Board.builder()
                .author("박규태")

                .content("내용")
                .createdTime(LocalDateTime.now())
                .subject("제목")
                .build();

        Board newBoard = boardRepository.save(requestData);

        //when
        newBoard.setAuthor("홍석춘");

        Board selectBoard = boardRepository.findById(newBoard.getId()).get();

        //then
        assertEquals("홍석춘", selectBoard.getAuthor());
        assertEquals(requestData.getAuthor(), selectBoard.getAuthor());
        assertEquals(newBoard.getId(), selectBoard.getId());
        assertEquals(requestData, newBoard);
        assertEquals(selectBoard, newBoard);
    }

    @Test
    @Rollback(false)
    public void Board동일Entity중복등록테스트() {
        //given
        Board requestData = Board.builder()
                .author("박규태1")
                .content("중복등록테스트")
                .createdTime(LocalDateTime.now())
                .subject("제목")
                .build();

        Board newBoard = boardRepository.save(requestData);
        Board newBoard2 = boardRepository.save(requestData);

        //when
        List<Board> all = boardRepository.findAll();

        //then
        assertEquals(all.size(), 2);
    }

}