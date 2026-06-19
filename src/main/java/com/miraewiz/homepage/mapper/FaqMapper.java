package com.miraewiz.homepage.mapper;

import com.miraewiz.homepage.model.Faq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface FaqMapper {
    List<Faq> findAll();
    List<Faq> findAllAdmin(@Param("search") String search, @Param("offset") int offset, @Param("limit") int limit);
    int countAllAdmin(@Param("search") String search);
    Faq findById(Long id);
    void insert(Faq faq);
    void update(Faq faq);
    void delete(Long id);
}
