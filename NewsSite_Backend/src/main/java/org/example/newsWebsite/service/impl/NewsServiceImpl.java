package org.example.newsWebsite.service.impl;

import org.example.newsWebsite.model.News;
import org.example.newsWebsite.model.View;
import org.example.newsWebsite.repository.NewsRepository;
import org.example.newsWebsite.repository.UserRepository;
import org.example.newsWebsite.repository.ViewRepository;
import org.example.newsWebsite.service.api.NewsService;
import org.example.newsWebsite.service.api.uploading.StorageService;
import org.example.newsWebsite.service.impl.uploading.FileSystemStorageService;
import org.example.newsWebsite.service.impl.uploading.StorageDirectory;
import org.example.newsWebsite.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
 class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ViewRepository viewRepository;

    @Autowired
    private UserRepository userRepository;

    private StorageService storageService;

    public NewsServiceImpl() {
        this.storageService = new FileSystemStorageService(StorageDirectory.NEWS);
    }

    @Override
    public News addNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News editNews(News news) {
        if (news.getId() == null) {
            return null;
        }
        if (this.newsRepository.existsById(news.getId())) {
            News n = newsRepository.getById(news.getId());

            if(Objects.nonNull(news.getTitle()) && !news.getTitle().isEmpty()) {
                n.setTitle(news.getTitle());
            }
            if(Objects.nonNull(news.getCategory()) && !news.getCategory().isEmpty()) {
                n.setCategory(news.getCategory());
            }
            if(Objects.nonNull(news.getDate())) {
                n.setDate(news.getDate());
            }

            return newsRepository.save(n);
        }
        return null;
    }

    @Override
    public Boolean uploadNewsImage(MultipartFile file, Long id) {
        if (isExistNews(id)){

            String fileName = id + "." + StringUtils.extractPostfix(file.getOriginalFilename());
            boolean status =  storageService.store(file, Paths.get("photo"), fileName);

            if(status){

                News news = this.getNews(id);
                news.setPhotoPath(fileName);
                newsRepository.save(news);
                return true;
            }
        }
        return false;
    }

    @Override
    public Resource downloadImage(Long id) {
        News news = this.getNews(id);

        if(news != null){

            String filename = news.getPhotoPath();
            if(filename != null){
                try {
                    return storageService.loadAsResource(Paths.get("photo"), filename);
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getAllNewsConfirmed() {
        List<News> news = new ArrayList<>();

        for(News n : newsRepository.findAll()) {
            if(n.getVerification())
                news.add(n);
        }
        return news;
    }

    @Override
    public List<News> getAllNewsNotConfirmed() {
        List<News> news = new ArrayList<>();

        for(News n : newsRepository.findAll()) {
            if(!n.getVerification())
                news.add(n);
        }
        return news;    }

    @Override
    public List<News> getNewsByCategory(String category) {
        List<News> news = new ArrayList<>();

        for(News n : newsRepository.findAll()) {
            if(n.getCategory().equals(category) &&
               n.getVerification())
                news.add(n);
        }
        return news;
    }

    @Override
    public List<News> getNewsByName(String name) {
        List<News> news = new ArrayList<>();

        for(News n : newsRepository.findAll()) {
            if(n.getTitle().equals(name))
                news.add(n);
        }
        return news;
    }

    @Override
    public News getNewsById(Long id) {
        if(newsRepository.existsById(id)) {
            return newsRepository.getById(id);
        }
        return null;
    }

    @Override
    public News getNews(Long id) {
        if(newsRepository.existsById(id)) {
            return newsRepository.getById(id);
        }
        return null;
    }

    public Integer incrementNewsViewer(Long newsId, Long userId) {
        for(View view : viewRepository.findAll()) {
            if(view.getNews().getId().equals(newsId) &&
               view.getUser().getId().equals(userId)) {
                return null;
            }
        }

        View view = new View(null, userRepository.findById(userId).get(), newsRepository.findById(newsId).get());
        viewRepository.save(view);

        News news = newsRepository.getById(newsId);
        news.setViewNumber(news.getViewNumber() + 1);
        newsRepository.save(news);
        return news.getViewNumber();
    }

    @Override
    public boolean deleteNews(Long id) {
        if(this.newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistNews(Long id) {
        return newsRepository.existsById(id);
    }

    @Override
    public List<News> getLastNews(Long time) {
        List<News> news = new ArrayList<>();

//       Date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//       Current format
        Date now = new Date();

        for(News n : newsRepository.findAll()) {
            try{
                Date date = sdf.parse(n.getDate());

                Long diff = now.getTime() - date.getTime();

                if(diff < time &&
                   n.getVerification()){
                    news.add(n);
                }
            } catch (Exception e){
                return null;
            }
        }
        return news;
    }

    @Override
    public List<News> getLastNews(Long time, String category) {
        List<News> news = new ArrayList<>();

//       Date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//       Current format
        Date now = new Date();

        for(News n : newsRepository.findAll()) {
            try{
                Date date = sdf.parse(n.getDate());

                Long diff = now.getTime() - date.getTime();

                if(diff < time &&
                   n.getCategory().equals(category) &&
                   n.getVerification()){
                    news.add(n);
                }
            } catch (Exception e){
                return null;
            }
        }
        return news;
    }

    @Override
    public List<News> getMostViews(Long time, Integer limit) {
        List<News> news = new ArrayList<>();

//       Date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//       Current format
        Date now = new Date();

        for(News n : newsRepository.findAll()) {
            try{
                Date date = sdf.parse(n.getDate());

                Long diff = now.getTime() - date.getTime();

                if(diff < time &&
                   n.getViewNumber() >= limit &&
                   n.getVerification()){
                    news.add(n);
                }
            } catch (Exception e){
                return null;
            }
        }
        return news;
    }

    public List<News> getMostViews(Long time, Integer limit, String category) {
        List<News> news = new ArrayList<>();

//       Date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//       Current format
        Date now = new Date();

        for(News n : newsRepository.findAll()) {
            try{
                Date date = sdf.parse(n.getDate());

                Long diff = now.getTime() - date.getTime();

                if(diff < time &&
                   n.getViewNumber() >= limit &&
                   n.getCategory().equals(category) &&
                   n.getVerification()){
                    news.add(n);
                }
            } catch (Exception e){
                return null;
            }
        }
        return news;
    }

    public Integer getAllVisits() {
        Integer visits = 0;
        for(News n : newsRepository.findAll()) {
            if(n.getVerification())
                visits += n.getViewNumber();
        }
        return visits;
    }

    @Override
    public Integer getAllVisits(Long userId) {
        Integer visits = 0;
        for(News n : newsRepository.findAll()) {
            if(n.getAuthor().getId().equals(userId) &&
               n.getVerification()){
                visits += n.getViewNumber();
            }
        }
        return visits;
    }

    @Override
    public Integer getAllVisits(String username) {
        Integer visits = 0;
        for(News n : newsRepository.findAll()) {
            if(n.getAuthor().getUsername().equals(username) &&
               n.getVerification()){
                visits += n.getViewNumber();
            }
        }
        return visits;
    }

    @Override
    public Integer getAllVisitsByCategory(String category) {
        Integer visits = 0;

        for(News n : newsRepository.findAll()) {
            if(n.getCategory().equals(category) &&
               n.getVerification()){
                visits += n.getViewNumber();
            }
        }
        return visits;
    }

    @Override
    public Integer getNumberOfAllNews() {
        return newsRepository.findAll().size();
    }

    @Override
    public Integer getNumberOfAllNewsbyCategory(String category) {
        Integer couter = 0;
        for(News n : newsRepository.findAll()) {
            if(n.getCategory().equals(category) &&
               n.getVerification()){
                couter++;
            }
        }
        return couter;
    }

    @Override
    public Integer getNumberOfAllNews(Long userId) {
        Integer counter = 0;
        for(News n : newsRepository.findAll()) {
            if(n.getAuthor().getId().equals(userId) &&
               n.getVerification()){
                counter++;
            }
        }
        return counter;
    }

    @Override
    public Integer getNumberOfAllNews(String username) {
        Integer counter = 0;
        for(News n : newsRepository.findAll()) {
            if(n.getAuthor().getUsername().equals(username) &&
               n.getVerification()){
                counter++;
            }
        }
        return counter;
    }

    @Override
    public Boolean confirmNews(Long id) {
        if(this.isExistNews(id)){
            News news = newsRepository.findById(id).get();
            news.setVerification(true);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean rejectNews(Long id) {
        return this.deleteNews(id);
    }
}
