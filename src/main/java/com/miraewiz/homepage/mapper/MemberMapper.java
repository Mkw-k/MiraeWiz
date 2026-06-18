package com.miraewiz.homepage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.Map;

@Mapper
public interface MemberMapper {
    @Select("SELECT * FROM members WHERE username = #{username}")
    Map<String, Object> findByUsername(String username);
}
