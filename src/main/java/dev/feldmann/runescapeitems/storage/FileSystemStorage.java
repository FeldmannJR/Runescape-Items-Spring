package dev.feldmann.runescapeitems.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
public class FileSystemStorage implements StorageService {

    @Value("${storage.location:./storage/}")
    private String storageLocation;


    @Override
    public String store(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return null;
        }
        String ext = originalFilename.substring(originalFilename.indexOf('.') + 1);
        String fileName = UUID.randomUUID().toString() + "." + ext;
        try (InputStream is = file.getInputStream()) {
            Path fullPath = getPathFromFilename(fileName);
            System.out.println("File saved at " + fullPath.toAbsolutePath().toString());
            Files.copy(is, fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public boolean delete(String fileName) {
        Path pathFromFilename = null;
        try {
            pathFromFilename = getPathFromFilename(fileName);
            Files.delete(pathFromFilename);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean exists(String path) {
        try {
            return Files.exists(getPathFromFilename(path));
        } catch (IOException e) {
            throw new StorageException("IOException in test file existence", e);
        }
    }

    @Override
    public List<String> listAll() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public Resource loadAsResource(String fileName) {
        if (!exists(fileName)) {
            throw new StorageFileNotFoundException(fileName);
        }
        try {
            Path path = getPathFromFilename(fileName);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() && resource.isFile() && resource.isReadable()) {
                return resource;
            }
            throw new StorageException("Cannot read resource " + fileName);
        } catch (IOException e) {
            throw new StorageException("Cannot load " + fileName + " resource");
        }


    }

    private Path getPathFromFilename(String fileName) throws IOException {
        if (fileName.contains("..")) {
            throw new IOException("Could not initialize file outside storage location");
        }

        Path path = getStoragePath().resolve(fileName);
        return path;
    }

    private Path getStoragePath() {
        File storageDir = new File(storageLocation);
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }
        if (!storageDir.isDirectory()) {
            throw new RuntimeException("Storage path is a file!");
        }
        return storageDir.toPath();
    }
}
