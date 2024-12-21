package org.example.newsWebsite.service.impl.uploading;

import jakarta.annotation.PreDestroy;
import org.example.newsWebsite.service.api.uploading.StorageService;
import org.example.newsWebsite.service.api.uploading.exception.StorageException;
import org.example.newsWebsite.service.api.uploading.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {
    private Path rootLocation;

    public FileSystemStorageService() {
        Path rootLocation = Paths.get("src/uploadingFile/user");
//        if(path.trim().isEmpty()){
//            throw new StorageException("File upload location can not be Empty.");
//        }
//
//        this.rootLocation = Paths.get(path);
    }

//    @PreDestroy
    @Override
    public boolean store(MultipartFile file, String fileName) {
        try {
            File f = new File("src/uploadingFile/user/" + fileName);
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(file.getBytes());
            fos.close();
            fos.flush();
            return true;

//            if (file.isEmpty()) {
//                throw new StorageException("Failed to store empty file.");
//            }
//            Path destinationFile = this.rootLocation.resolve(
//                            Paths.get(fileName))
//                                           .normalize().toAbsolutePath();
//            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
////                 This is a security check
//                throw new StorageException(
//                        "Cannot store file outside current directory.");
//            }
//            try (InputStream inputStream = file.getInputStream()) {
//                Files.copy(inputStream,
//                           destinationFile,
//                           StandardCopyOption.REPLACE_EXISTING);
//            }
//            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                           .filter(path -> !path.equals(this.rootLocation))
                           .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @PreDestroy
    public void destroy() {
    }
}
