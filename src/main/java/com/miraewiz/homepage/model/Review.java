package com.miraewiz.homepage.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Review {
    private Long id;
    private String author;
    private String password;
    private Long memberId;
    private String content;
    private Integer rating;
    private Boolean isBest;
    private Boolean isVisible;
    private LocalDateTime createdAt;
}
