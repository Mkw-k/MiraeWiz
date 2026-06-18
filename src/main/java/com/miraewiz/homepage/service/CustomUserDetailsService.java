package com.miraewiz.homepage.service;

import com.miraewiz.homepage.mapper.MemberMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    public CustomUserDetailsService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> member = memberMapper.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // H2 and many databases return column names in UPPERCASE when mapped to a generic Map
        String dbUsername = (String) member.get("USERNAME");
        String dbPassword = (String) member.get("PASSWORD");

        if (dbUsername == null) {
             // Fallback just in case MySQL returns lowercase
             dbUsername = (String) member.get("username");
             dbPassword = (String) member.get("password");
        }

        return User.builder()
                .username(dbUsername)
                .password(dbPassword)
                .roles("ADMIN")
                .build();
    }
}
