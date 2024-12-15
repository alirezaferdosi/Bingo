package org.example.newsWebsite;

import org.example.newsWebsite.service.api.uploading.properties.NewsStorageProperties;
import org.example.newsWebsite.service.api.uploading.properties.UserStorageProperties;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "org.example.newsWebsite")
@EnableConfigurationProperties({UserStorageProperties.class, NewsStorageProperties.class})
public class NewsWebSiteApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NewsWebSiteApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
