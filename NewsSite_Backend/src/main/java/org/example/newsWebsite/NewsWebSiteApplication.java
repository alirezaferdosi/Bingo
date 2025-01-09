package org.example.newsWebsite;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example.newsWebsite")
public class NewsWebSiteApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NewsWebSiteApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
