package org.example.newsWebsite.service.api.uploading.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@Primary
@ConfigurationProperties(prefix = "news-storage")
public class NewsStorageProperties implements StorageProperties{
    /**
     *  Folder location for storage news photo
     */
    String location = "src/uploadingFile/news";

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }
}
