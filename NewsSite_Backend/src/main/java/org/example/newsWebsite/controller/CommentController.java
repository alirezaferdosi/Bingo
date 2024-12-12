package org.example.newsWebsite.controller;

import org.example.newsWebsite.model.dto.CommentDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("comment")
public class CommentController {
    @GetMapping("all")
    public List<CommentDto> getAllComments(@RequestParam(name = "newsid") Long newsId) {
        return null;
    }

    @PostMapping("add")
    public CommentDto addComment(@RequestBody @Validated CommentDto commentDto) {
        return null;
    }

    @PutMapping("edit")
    public CommentDto editComment(@RequestBody @Validated CommentDto commentDto, @RequestParam(name = "id") Long id) {
        return null;
    }

    @DeleteMapping("delete")
    public boolean deleteComment(@RequestParam(name = "id") Long id) {
        return false;
    }
}
