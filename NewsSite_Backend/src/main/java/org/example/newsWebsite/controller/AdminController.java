package org.example.newsWebsite.controller;

import org.example.newsWebsite.service.api.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    @Qualifier("newsServiceImpl")
    private NewsService newsService;


    @GetMapping("confirmNews")
    public ResponseEntity<Boolean> confirmNews(@RequestParam(name = "id") Long id) {
        boolean status = newsService.confirmNews(id);
        return ResponseEntity
                       .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                       .body(status);
    }

    @GetMapping("rejectNews")
    public ResponseEntity<Boolean> rejectNews(@RequestParam(name = "id") Long id) {
        boolean status = newsService.rejectNews(id);
        return ResponseEntity
                       .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                       .body(status);    }
}
