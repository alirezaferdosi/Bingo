package org.example.newsWebsite.controller;

import org.example.newsWebsite.model.News;
import org.example.newsWebsite.model.convertor.PrimitiveConvertor;
import org.example.newsWebsite.model.dto.NewsDto;
import org.example.newsWebsite.service.api.NewsService;
import org.example.newsWebsite.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@RequestMapping("news")
public class NewsController {
    @Autowired
    @Qualifier("newsServiceImpl")
    private NewsService newsService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("NewsConvertor")
    private PrimitiveConvertor<News, NewsDto> convertor;


    @PostMapping("create")
    public ResponseEntity<NewsDto> create(@RequestBody NewsDto newsDto) {
        newsDto.setId(null);
        newsDto.setDate(LocalDate.now().toString());
        newsDto.setVerification(false);
        newsDto.setViewNumber(0);

        if(userService.isUserExist(newsDto.getAuthor())){
            NewsDto n = convertor.modedToDto(
                    newsService.addNews(
                            convertor.dtoToModed(newsDto)
                    )
            );
            return ResponseEntity
                           .status(HttpStatus.CREATED)
                           .body(n);
        }
        else {
            return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .body(null);
        }
    }

    @PutMapping("edit")
    public ResponseEntity<NewsDto> edit(@RequestBody NewsDto newsDto) {
        newsDto.setDate(LocalDate.now().toString());

        News n = newsService.editNews(convertor.dtoToModed(newsDto));

        if (n == null) {
            return ResponseEntity
                           .status(HttpStatus.NO_CONTENT)
                           .body(null);
        }
        else {
            NewsDto newsDto1 = convertor.modedToDto(n);
            return ResponseEntity
                           .status(HttpStatus.OK)
                           .body(newsDto1);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<NewsDto>> getAllNews() {
        List<NewsDto> newsDtos = new ArrayList<>();

        for(News news : newsService.getAllNews()) {
            newsDtos.add(convertor.modedToDto(news));
        }

        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsDtos);
    }

    @GetMapping("confirmed")
    public ResponseEntity<List<NewsDto>> getConfirmedNews() {
        List<NewsDto> newsDtos = new ArrayList<>();

        for(News news : newsService.getAllNewsConfirmed()) {
            newsDtos.add(convertor.modedToDto(news));
        }

        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsDtos);
    }

    @GetMapping("byCategory")
    public ResponseEntity<List<NewsDto>> getNewsByCategory(@RequestParam("c") String category) {
        List<NewsDto> newsDtos = new ArrayList<>();

        for(News news : newsService.getNewsByCategory(category)) {
            newsDtos.add(convertor.modedToDto(news));
        }

        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsDtos);
    }

    @PutMapping("incrementViewer")
    public ResponseEntity<Integer> incrementNewsViewer(@RequestParam("id") Long id) {
        if (id == null) {
            return ResponseEntity
                           .status(HttpStatus.BAD_REQUEST)
                           .body(null);
        }
        else {
            Integer number = newsService.incrementNewsViewer(id);
            if (number == null) {
                return ResponseEntity
                               .status(HttpStatus.NOT_FOUND)
                               .body(null);
            }
            else {
                return ResponseEntity
                               .status(HttpStatus.OK)
                               .body(number);
            }
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteNews(@RequestParam("id") Long id) {
        if(newsService.deleteNews(id)) {
            return ResponseEntity
                           .status(HttpStatus.OK)
                           .build();
        }
        else {
            return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .build();
        }
    }
}

