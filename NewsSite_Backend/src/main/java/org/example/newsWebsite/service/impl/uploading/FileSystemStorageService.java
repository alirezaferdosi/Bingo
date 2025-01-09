package org.example.newsWebsite.service.impl.uploading;

import org.example.newsWebsite.service.api.uploading.StorageService;
import org.example.newsWebsite.service.api.uploading.exception.StorageException;
import org.example.newsWebsite.service.api.uploading.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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

//@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;

    public FileSystemStorageService(StorageDirectory storageDirectory) {
        if(storageDirectory.getPath().trim().isEmpty()){
            throw new StorageException("File upload location can not be Empty.");
        }

        this.rootLocation = Paths.get(storageDirectory.getPath());
    }

//    @PreDestroy
    @Override
    public Boolean store(MultipartFile file, String fileName) {
        try {
            File f = new File(this.rootLocation + "/" + fileName);
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
    public Boolean store(MultipartFile file, Path subDirectory, String fileName) {
        try {
            File f = new File(this.rootLocation + "/" + subDirectory + "/" + fileName);
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
        }    }

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
    public Path load(Path subDirectory, String filename) {
        return Paths.get(this.rootLocation + "/" + subDirectory)
                       .resolve(filename);
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
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public Resource loadAsResource(Path subDirectory, String filename) {
        try {
            Path file = load(subDirectory, filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public Path getPath() {
        return this.rootLocation;
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
}
