package org.example.newsWebsite.service.impl.uploading;

public enum StorageDirectory {
    USER("src/uploadingFile/user"),
    NEWS("src/uploadingFile/news");

    private final String path;

    StorageDirectory(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static StorageDirectory getInstance(String path) {
        for (StorageDirectory storageDirectory : StorageDirectory.values()) {
            if (storageDirectory.getPath().equals(path)) {
                return storageDirectory;
            }
        }
        return null;
    }
}
