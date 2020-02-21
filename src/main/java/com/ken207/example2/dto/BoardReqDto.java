package com.ken207.example2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
public class BoardReqDto {
    private String author;
    private String subject;
    private String content;
    private String password;
}
