package com.miraewiz.homepage.mapper;

import com.miraewiz.homepage.model.Program;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProgramMapper {
    List<Program> findAll();
    List<Program> findAllVisible();
    Program findById(Long id);
    void insert(Program program);
    void update(Program program);
    void delete(Long id);
}
