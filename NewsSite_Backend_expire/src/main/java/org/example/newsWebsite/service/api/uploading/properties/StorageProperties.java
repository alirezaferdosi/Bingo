package org.example.newsWebsite.service.api.uploading.properties;

import org.springframework.stereotype.Component;

@Component("storageProperties")
public interface StorageProperties {
    /**
     *  Folder location for storage profile photo
     */
    String Location = "src/uploadingFile/user";

    String getLocation();

    void setLocation(String location);
}
