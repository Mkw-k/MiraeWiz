package com.miraewiz.homepage.controller.api;

import com.miraewiz.homepage.mapper.FaqMapper;
import com.miraewiz.homepage.model.Faq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/faqs")
public class AdminFaqApiController {

    private final FaqMapper faqMapper;

    public AdminFaqApiController(FaqMapper faqMapper) {
        this.faqMapper = faqMapper;
    }

    @GetMapping
    public List<Faq> getFaqs() {
        return faqMapper.findAll();
    }

    @PostMapping
    public ResponseEntity<Faq> createFaq(@RequestBody Faq faq) {
        faqMapper.insert(faq);
        return ResponseEntity.ok(faq);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faq> updateFaq(@PathVariable Long id, @RequestBody Faq faq) {
        faq.setId(id);
        faqMapper.update(faq);
        return ResponseEntity.ok(faq);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaq(@PathVariable Long id) {
        faqMapper.delete(id);
        return ResponseEntity.noContent().build();
    }
}
