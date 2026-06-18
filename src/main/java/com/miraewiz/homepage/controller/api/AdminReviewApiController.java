package com.miraewiz.homepage.controller.api;

import com.miraewiz.homepage.mapper.ReviewMapper;
import com.miraewiz.homepage.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reviews")
public class AdminReviewApiController {

    private final ReviewMapper reviewMapper;

    public AdminReviewApiController(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @GetMapping
    public List<Review> getReviews() {
        return reviewMapper.findAll();
    }

    @PatchMapping("/{id}/visibility")
    public ResponseEntity<Void> updateVisibility(@PathVariable Long id, @RequestParam Boolean isVisible) {
        reviewMapper.updateVisibility(id, isVisible);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewMapper.delete(id);
        return ResponseEntity.noContent().build();
    }
}
