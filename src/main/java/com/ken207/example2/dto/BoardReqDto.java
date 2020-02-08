package com.ken207.example2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardReqDto {
    private String author;
    private String subject;
    private String content;
    private String password;
}
