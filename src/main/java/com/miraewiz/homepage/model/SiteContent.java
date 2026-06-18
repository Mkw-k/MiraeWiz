package com.miraewiz.homepage.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SiteContent {
    private String contentKey;
    private String contentValue;
    private String description;
    private LocalDateTime updatedAt;
}
