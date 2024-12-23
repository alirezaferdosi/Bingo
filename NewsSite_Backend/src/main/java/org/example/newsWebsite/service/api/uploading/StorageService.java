package org.example.newsWebsite.service.api.uploading;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

//@Component("storageService")
public interface StorageService {
    void init();

    Boolean store(MultipartFile file, Path subDirectory, String fileName);

    Boolean store(MultipartFile file, String fileName);

    Stream<Path> loadAll();

    Path load(String filename);

    Path load(Path subDirectory, String filename);

    Resource loadAsResource(String filename);

    Resource loadAsResource(Path subDirectory, String filename);

    void deleteAll();

    Path getPath();
}
