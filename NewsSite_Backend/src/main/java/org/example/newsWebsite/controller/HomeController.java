package org.example.newsWebsite.controller;

import org.example.newsWebsite.service.api.NewsService;
import org.example.newsWebsite.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {
    @Autowired
    @Qualifier("newsServiceImpl")
    private NewsService newsService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @GetMapping("allVisits")
    public ResponseEntity<Integer> allVisits() {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsService.getAllVisits());
    }

    @GetMapping("allNews")
    public ResponseEntity<Integer> allNews() {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsService.getNumberOfAllNews());
    }

    @GetMapping("allUsers")
    public ResponseEntity<Integer> allUsers() {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(userService.getNumberOfUsers());
    }

}
