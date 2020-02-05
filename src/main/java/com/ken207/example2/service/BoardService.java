package com.ken207.example2.service;

import com.ken207.example2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public Long postBoard() {
        return 0L;
    }
}
