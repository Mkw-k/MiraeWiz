package com.miraewiz.homepage.controller.api;

import com.miraewiz.homepage.mapper.FaqMapper;
import com.miraewiz.homepage.model.Faq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/admin/faqs")
public class AdminFaqApiController {

    private final FaqMapper faqMapper;

    public AdminFaqApiController(FaqMapper faqMapper) {
        this.faqMapper = faqMapper;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getFaqs(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        int offset = (page - 1) * size;
        List<Faq> faqs = faqMapper.findAllAdmin(search, offset, size);
        int totalElements = faqMapper.countAllAdmin(search);
        int totalPages = (int) Math.ceil((double) totalElements / size);

        Map<String, Object> response = new HashMap<>();
        response.put("content", faqs);
        response.put("totalElements", totalElements);
        response.put("totalPages", totalPages > 0 ? totalPages : 1);
        response.put("currentPage", page);

        return ResponseEntity.ok(response);
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
