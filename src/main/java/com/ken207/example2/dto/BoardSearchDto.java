package com.ken207.example2.dto;

import com.ken207.example2.enums.SearchType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardSearchDto {
    private SearchType searchType;
    private String searchStr;
}
