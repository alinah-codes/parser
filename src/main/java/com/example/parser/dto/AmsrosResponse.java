package com.example.parser.dto;

import lombok.Data;

import java.util.List;


@Data
public class AmsrosResponse {
    private List<AmsroDto> pageData;
    private Integer total;
}
