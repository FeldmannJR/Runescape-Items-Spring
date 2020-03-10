package dev.feldmann.runescapeitems.controllers;

import dev.feldmann.runescapeitems.BaseController;
import dev.feldmann.runescapeitems.forms.CreateItemForm;
import dev.feldmann.runescapeitems.models.Image;
import dev.feldmann.runescapeitems.models.Item;
import dev.feldmann.runescapeitems.repositories.ItemRepository;
import dev.feldmann.runescapeitems.resources.ItemDTO;
import dev.feldmann.runescapeitems.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {

    private ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping("/")
    public ResponseEntity<ItemDTO> insert(@Valid CreateItemForm insert) {
        Image image = this.imageService.uploadImage(insert.getImage());
        Item item = new Item();
        item.setName(insert.getName());
        item.setHighAlchPrice(insert.getHighAlchPrice());
        item.setLowAlchPrice(insert.getLowAlchPrice());
        item.setStorePrice(insert.getStorePrice());
        item.setImage(image);
        this.itemRepository.save(item);
        return ResponseEntity
                .created(
                        MvcUriComponentsBuilder.fromMethodName(ItemController.class, "get", item.getId())
                                .build()
                                .toUri())
                .body(ItemDTO.from(item));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> get(@PathVariable("itemId") Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            return ResponseEntity.ok(ItemDTO.from(item.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{itemId}/image")
    @ResponseBody
    public ResponseEntity<Resource> serveItemImage(@PathVariable("itemId") Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            return this.serveImage(item.get().getImage());
        }
        return ResponseEntity.notFound().build();


    }


}
