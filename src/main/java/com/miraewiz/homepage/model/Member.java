package com.miraewiz.homepage.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Member {
    private Long id;
    private String username;
    private String password;
    private String role;
    private LocalDateTime createdAt;
}
