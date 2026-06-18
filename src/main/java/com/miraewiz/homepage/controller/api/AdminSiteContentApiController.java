package com.miraewiz.homepage.controller.api;

import com.miraewiz.homepage.mapper.SiteContentMapper;
import com.miraewiz.homepage.model.SiteContent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/contents")
public class AdminSiteContentApiController {

    private final SiteContentMapper siteContentMapper;

    public AdminSiteContentApiController(SiteContentMapper siteContentMapper) {
        this.siteContentMapper = siteContentMapper;
    }

    @GetMapping
    public List<SiteContent> getContents() {
        return siteContentMapper.findAll();
    }

    @PutMapping("/{key}")
    public ResponseEntity<SiteContent> updateContent(@PathVariable String key, @RequestBody SiteContent content) {
        content.setContentKey(key);
        siteContentMapper.update(content);
        return ResponseEntity.ok(content);
    }
}
