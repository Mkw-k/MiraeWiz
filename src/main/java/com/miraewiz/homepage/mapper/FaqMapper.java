package com.miraewiz.homepage.mapper;

import com.miraewiz.homepage.model.Faq;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface FaqMapper {
    List<Faq> findAll();
    Faq findById(Long id);
    void insert(Faq faq);
    void update(Faq faq);
    void delete(Long id);
}
