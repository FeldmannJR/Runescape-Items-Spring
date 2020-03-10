package dev.feldmann.runescapeitems.services;

import dev.feldmann.runescapeitems.models.Image;
import dev.feldmann.runescapeitems.repositories.ImageRepository;
import dev.feldmann.runescapeitems.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private StorageService storage;
    private ImageRepository repository;

    @Autowired
    public ImageService(StorageService storage, ImageRepository repository) {
        this.storage = storage;
        this.repository = repository;
    }

    public Image uploadImage(MultipartFile file) {
        String path = storage.store(file);
        Image image = new Image();
        image.setPath(path);
        image.setMimeType(file.getContentType());
        repository.save(image);
        return image;
    }

    public Resource asResource(Image image) {
        return storage.loadAsResource(image.getPath());
    }

    public MediaType getMediaType(Image image) {
        MimeType mimeType = MimeTypeUtils.parseMimeType(image.getMimeType());
        return MediaType.asMediaType(mimeType);
    }
}
