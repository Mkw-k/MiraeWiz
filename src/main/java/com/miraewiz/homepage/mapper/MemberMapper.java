package com.miraewiz.homepage.mapper;

import com.miraewiz.homepage.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
    @Select("SELECT * FROM members WHERE username = #{username}")
    Member findByUsername(String username);
}
