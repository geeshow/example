package com.ken207.example2.domain;

import com.ken207.example2.exception.BizRuntimeException;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue
    private Long id;

    private String author;
    private String subject;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedDate;
    private int hitCount;
    private boolean delYn;
    private String password;

    @OneToMany(mappedBy = "board")
    private List<Comments> commentsList = new ArrayList();

    public static Board postBoard(String author, String subject, String content, String password) {
        Board newBoard = Board.builder()
                .author(author)
                .subject(subject)
                .content(content)
                .createdTime(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .hitCount(0)
                .delYn(false)
                .build();

        newBoard.setPassword(password);
        return newBoard;
    }

    public void setPassword(String password) {
        if ( password == null ) {
            throw new BizRuntimeException("비밀번호는 필수값 입니다.");
        }
        else if ( password.length() < 8 ) {
            throw new BizRuntimeException("비밀번호는 8자리 이상 영문,숫자로 조합해야 합니다.");
        }

        this.password = password;

    }

    public void read() {
        this.hitCount++;
    }

    public void adjust(String subject, String content, String inputPassword) {
        if ( checkPassword(inputPassword) ) {
            throw new BizRuntimeException("비밀번호 불일치");
        }

        this.subject = subject;
        this.content = content;
        this.modifiedDate = LocalDateTime.now();

        setPassword(inputPassword);
    }

    public void delete(String inputPassword) {
        if ( checkPassword(inputPassword) ) {
            throw new BizRuntimeException("비밀번호 불일치");
        }
        this.delYn = true;
    }

    private boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public Comments writeComment(String content) {
        Comments newComment = Comments.builder()
                .content(content)
                .createdTime(LocalDateTime.now())
                .build();

        // 양방향 연관관계 정의
        commentsList.add(newComment);
        newComment.setBoard(this);

        return newComment;
    }
}
