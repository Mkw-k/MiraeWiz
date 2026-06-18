package com.miraewiz.homepage.mapper;

import com.miraewiz.homepage.model.Program;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProgramMapper {
    List<Program> findAllVisible();
    void insert(Program program);
}
