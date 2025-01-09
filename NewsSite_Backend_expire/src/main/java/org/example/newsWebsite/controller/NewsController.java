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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private LocalDateTime localDateTime;
    private DateTimeFormatter formatter;


    @PostMapping("create")
    public ResponseEntity<NewsDto> create(@RequestBody NewsDto newsDto) {
        localDateTime = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        newsDto.setId(null);
        newsDto.setDate(localDateTime.format(formatter));
        newsDto.setVerification(false);
        newsDto.setViewNumber(0);

        if(userService.isUserExist(newsDto.getAuthor())){
            NewsDto n = convertor.modelToDto(
                    newsService.addNews(
                            convertor.dtoToModel(newsDto)
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
        localDateTime = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        newsDto.setDate(localDateTime.format(formatter));

        News n = newsService.editNews(convertor.dtoToModel(newsDto));

        if (n == null) {
            return ResponseEntity
                           .status(HttpStatus.NO_CONTENT)
                           .body(null);
        }
        else {
            NewsDto newsDto1 = convertor.modelToDto(n);
            return ResponseEntity
                           .status(HttpStatus.OK)
                           .body(newsDto1);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<NewsDto>> getAllNews() {
        List<NewsDto> newsDtos = new ArrayList<>();

        for(News news : newsService.getAllNews()) {
            newsDtos.add(convertor.modelToDto(news));
        }

        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsDtos);
    }

    @GetMapping("confirmed")
    public ResponseEntity<List<NewsDto>> getConfirmedNews() {
        List<NewsDto> newsDtos = new ArrayList<>();

        for(News news : newsService.getAllNewsConfirmed()) {
            newsDtos.add(convertor.modelToDto(news));
        }

        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsDtos);
    }

    @GetMapping("byCategory")
    public ResponseEntity<List<NewsDto>> getNewsByCategory(@RequestParam(name = "c") String category) {
        List<NewsDto> newsDtos = new ArrayList<>();

        for(News news : newsService.getNewsByCategory(category)) {
            newsDtos.add(convertor.modelToDto(news));
        }

        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsDtos);
    }

    @PutMapping("incrementViewer")
    public ResponseEntity<Integer> incrementNewsViewer(@RequestParam(name = "n") Long newsId,
                                                       @RequestParam(name = "u") Long userId) {
        if(!this.userService.isUserExist(userId) || !this.userService.isUserExist(newsId)) {
            return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .build();
        }

        Integer number = newsService.incrementNewsViewer(newsId, userId);
        if (number == null) {
            return ResponseEntity
                           .status(HttpStatus.BAD_REQUEST)
                           .body(null);
        }
        else {
                return ResponseEntity
                               .status(HttpStatus.OK)
                               .body(number);
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

    @GetMapping("lastNews")
    public ResponseEntity<List<NewsDto>> getLastNews(@RequestParam(name = "t",  defaultValue = "256200000") Long time,
                                                     @RequestParam(name = "c",  defaultValue = "") String category) {

        List<News> news;
        if(category.isEmpty()){
            news = this.newsService.getLastNews(time);
        }
        else {
            news = this.newsService.getLastNews(time, category);
        }

        if(news.isEmpty()) {
            return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .build();
        }
        else {
            List<NewsDto> newsDtos = new ArrayList<>();
            for (News n: news) {
                newsDtos.add(convertor.modelToDto(n));
            }
            return ResponseEntity
                           .status(HttpStatus.OK)
                           .body(newsDtos);
        }
    }

    @GetMapping("mostViews")
    public ResponseEntity<List<NewsDto>> getMostViews(@RequestParam(name = "t", defaultValue = "256200000") Long time,
                                                      @RequestParam(name = "n", defaultValue = "10") Integer viewNumber,
                                                      @RequestParam(name = "c", defaultValue = "") String category) {

        List<News> news;
        if(category.isEmpty()){
            news = this.newsService.getMostViews(time, viewNumber);
        }
        else {
            news = this.newsService.getMostViews(time, viewNumber, category);
        }

        if(news.isEmpty()) {
            return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .build();
        }
        else {
            List<NewsDto> newsDtos = new ArrayList<>();
            for (News n: news) {
                newsDtos.add(convertor.modelToDto(n));
            }
            return ResponseEntity
                           .status(HttpStatus.OK)
                           .body(newsDtos);
        }
    }

    @GetMapping("viewsByCategory")
    public ResponseEntity<Integer> getViewsByCategory(@RequestParam(name = "c") String category) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsService.getAllVisitsByCategory(category));
    }

    @GetMapping("newsByCategory")
    public ResponseEntity<Integer> getNumberOfNewsByCategory(@RequestParam(name = "c") String category) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsService.getNumberOfAllNewsbyCategory(category));
    }
}
