package org.example.newsWebsite.controller;

import org.example.newsWebsite.model.dto.NewsDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("news")
public class NewsController {
    @PostMapping("create")
    public NewsDto create(@RequestBody NewsDto newsDto) {
        return null;
    }

    @PutMapping("edit")
    public NewsDto edit(@RequestBody NewsDto newsDto) {
        return null;
    }

    @GetMapping("all")
    public List<NewsDto> getAllNews() {
        return null;
    }

    @GetMapping("confirmed")
    public List<NewsDto> getConfirmedNews() {
        return null;
    }

    @GetMapping("ByCategory")
    public List<NewsDto> getNewsByCategory(@RequestParam String category) {
        return null;
    }

    @PutMapping("incrementViewer")
    public Integer incrementNewsViewer(@RequestBody Long id) {
        return null;
    }
}
