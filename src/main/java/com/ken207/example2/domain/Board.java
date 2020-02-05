package com.ken207.example2.domain;

import com.ken207.example2.exception.BizRuntimeException;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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

    public static Board postBoard(String author, String subject, String content, String password) {
        return Board.builder()
                .author(author)
                .subject(subject)
                .content(content)
                .password(password)
                .createdTime(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .hitCount(0)
                .delYn(false)
                .build();
    }

    public void read() {
        this.hitCount++;
    }

    public void adjust(String subject, String content, String inputPassword) throws Exception {
        if ( checkPassword(inputPassword) ) {
            throw new BizRuntimeException("비밀번호 불일치");
        }
        this.subject = subject;
        this.content = content;
        this.modifiedDate = LocalDateTime.now();
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
}
