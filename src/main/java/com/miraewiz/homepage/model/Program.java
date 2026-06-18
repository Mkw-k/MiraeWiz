package com.miraewiz.homepage.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Program {
    private Long id;
    private String title;
    private String description;
    private String iconUrl;
    private String googlePlayUrl;
    private String appStoreUrl;
    private Integer displayOrder;
    private Boolean isVisible;
    private LocalDateTime createdAt;
}
