package com.ken207.example2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comments {
    @Id
    @GeneratedValue
    @Column(name = "comments_id")
    private Long id;
    private String name;
    private String content;
    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    /*
     * 연관관계 정의 메소드
     */
    public void setBoard(Board board) {
        this.board = board;
    }
}
