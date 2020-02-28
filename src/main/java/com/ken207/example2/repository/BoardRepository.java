package com.ken207.example2.repository;

import com.ken207.example2.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByAuthorLike(String searchStr);
    List<Board> findBySubjectLike(String searchStr);
    List<Board> findByContentLike(String searchStr);
    List<Board> findByTitleLike(String searchStr);
}
