package com.example.parser.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AmsroDto {
    private Integer arbitrManagerCount;
    private LocalDateTime dateOfRegistration;
    private String guid;
    private String inn;
    private Boolean isActive;
    private String name;
    private String ogrn;
    private String status;
}
