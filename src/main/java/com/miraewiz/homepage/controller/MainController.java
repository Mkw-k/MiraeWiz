package com.miraewiz.homepage.controller;

import com.miraewiz.homepage.mapper.FaqMapper;
import com.miraewiz.homepage.mapper.ProgramMapper;
import com.miraewiz.homepage.mapper.ReviewMapper;
import com.miraewiz.homepage.mapper.SiteContentMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final ProgramMapper programMapper;
    private final ReviewMapper reviewMapper;
    private final FaqMapper faqMapper;
    private final SiteContentMapper siteContentMapper;

    public MainController(ProgramMapper programMapper, ReviewMapper reviewMapper, 
                          FaqMapper faqMapper, SiteContentMapper siteContentMapper) {
        this.programMapper = programMapper;
        this.reviewMapper = reviewMapper;
        this.faqMapper = faqMapper;
        this.siteContentMapper = siteContentMapper;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("programs", programMapper.findAllVisible());
        model.addAttribute("bestReviews", reviewMapper.findAllVisible()); 
        model.addAttribute("vision", siteContentMapper.findByKey("ABOUT_VISION"));
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("greeting", siteContentMapper.findByKey("ABOUT_GREETING"));
        model.addAttribute("history", siteContentMapper.findByKey("ABOUT_HISTORY"));
        return "about";
    }

    @GetMapping("/programs")
    public String programs(Model model) {
        model.addAttribute("programs", programMapper.findAllVisible());
        return "programs";
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        model.addAttribute("faqs", faqMapper.findAll());
        return "faq";
    }
}
