package org.example.newsWebsite.service.api.uploading.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

@Primary
@ConfigurationProperties(prefix = "user-storage")
public class UserStorageProperties implements StorageProperties{
    /**
     *  Folder location for storage profile photo
     */
    String location = "src/uploadingFile";

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }
}
