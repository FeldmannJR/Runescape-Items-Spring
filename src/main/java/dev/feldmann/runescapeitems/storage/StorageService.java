package dev.feldmann.runescapeitems.storage;


import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface StorageService {

    String store(MultipartFile file);

    boolean delete(String fileName);

    boolean exists(String fileName);

    List<String> listAll();

    boolean deleteAll();

    Resource loadAsResource(String fileName);
}
