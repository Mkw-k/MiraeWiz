package com.miraewiz.homepage.service;

import com.miraewiz.homepage.mapper.MemberMapper;
import com.miraewiz.homepage.model.Member;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    public CustomUserDetailsService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.findByUsername(username);
        
        if (member == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles("ADMIN")
                .build();
    }
}
