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

        return User.builder()
                .username((String) member.get("username"))
                .password((String) member.get("password"))
                .roles("ADMIN")
                .build();
    }
}
