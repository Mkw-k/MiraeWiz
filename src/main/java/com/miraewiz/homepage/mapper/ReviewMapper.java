package com.miraewiz.homepage.mapper;

import com.miraewiz.homepage.model.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> findAll();
    List<Review> findAllAdmin(@Param("search") String search, @Param("offset") int offset, @Param("limit") int limit);
    int countAllAdmin(@Param("search") String search);
    List<Review> findAllVisible(@Param("offset") int offset, @Param("limit") int limit);
    int countVisible();
    Review findById(Long id);
    void insert(Review review);
    void update(Review review);
    void updateVisibility(@Param("id") Long id, @Param("isVisible") Boolean isVisible);
    void delete(Long id);
}
