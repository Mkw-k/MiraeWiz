package com.miraewiz.homepage.mapper;

import com.miraewiz.homepage.model.Review;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> findAll();
    List<Review> findAllVisible();
    Review findById(Long id);
    void insert(Review review);
    void update(Review review);
    void updateVisibility(Long id, Boolean isVisible);
    void delete(Long id);
}
