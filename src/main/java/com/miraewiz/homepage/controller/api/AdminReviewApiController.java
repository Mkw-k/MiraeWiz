package com.miraewiz.homepage.controller.api;

import com.miraewiz.homepage.mapper.ReviewMapper;
import com.miraewiz.homepage.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/admin/reviews")
public class AdminReviewApiController {

    private final ReviewMapper reviewMapper;

    public AdminReviewApiController(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getReviews(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        int offset = (page - 1) * size;
        List<Review> reviews = reviewMapper.findAllAdmin(search, offset, size);
        int totalElements = reviewMapper.countAllAdmin(search);
        int totalPages = (int) Math.ceil((double) totalElements / size);

        Map<String, Object> response = new HashMap<>();
        response.put("content", reviews);
        response.put("totalElements", totalElements);
        response.put("totalPages", totalPages > 0 ? totalPages : 1);
        response.put("currentPage", page);

        return ResponseEntity.ok(response);
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
