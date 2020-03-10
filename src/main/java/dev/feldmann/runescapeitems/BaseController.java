package dev.feldmann.runescapeitems;

import dev.feldmann.runescapeitems.models.Image;
import dev.feldmann.runescapeitems.models.Item;
import dev.feldmann.runescapeitems.services.ImageService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@NoArgsConstructor
@Controller
public class BaseController {


    protected ImageService imageService;

    protected ResponseEntity<Resource> serveImage(Image image) {

        if (image != null) {
            Resource file = imageService.asResource(image);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.asMediaType(imageService.getMediaType(image)))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                    .body(file);
        }
        return ResponseEntity.notFound().build();
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}
