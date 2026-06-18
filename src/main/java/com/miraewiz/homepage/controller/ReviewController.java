package com.miraewiz.homepage.controller;

import com.miraewiz.homepage.mapper.ReviewMapper;
import com.miraewiz.homepage.model.Review;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewMapper reviewMapper;
    private final PasswordEncoder passwordEncoder;

    public ReviewController(ReviewMapper reviewMapper, PasswordEncoder passwordEncoder) {
        this.reviewMapper = reviewMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewMapper.findAllVisible());
        return "reviews";
    }

    @PostMapping("/write")
    public String writeReview(Review review) {
        // Encode non-member password
        review.setPassword(passwordEncoder.encode(review.getPassword()));
        review.setIsVisible(true);
        review.setIsBest(false);
        reviewMapper.insert(review);
        return "redirect:/reviews";
    }

    @PostMapping("/delete")
    public String deleteReview(Long id, String password, Model model) {
        Review review = reviewMapper.findById(id);
        if (review != null && passwordEncoder.matches(password, review.getPassword())) {
            reviewMapper.delete(id);
            return "redirect:/reviews";
        } else {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("reviews", reviewMapper.findAllVisible());
            return "reviews";
        }
    }
}
