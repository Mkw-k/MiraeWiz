package com.miraewiz.homepage.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Faq {
    private Long id;
    private String question;
    private String answer;
    private String category;
    private Integer displayOrder;
    private LocalDateTime createdAt;
}
