package dev.feldmann.runescapeitems.resources;

import dev.feldmann.runescapeitems.controllers.ItemController;
import dev.feldmann.runescapeitems.models.Item;
import dev.feldmann.runescapeitems.validation.annotations.ValidImage;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemDTO {


    private Long id;
    private String name;
    private Long highAlchPrice;
    private Long lowAlchPrice;
    private Long storePrice;
    private String image;


    public static ItemDTO from(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setLowAlchPrice(item.getLowAlchPrice());
        dto.setHighAlchPrice(item.getHighAlchPrice());
        dto.setStorePrice(item.getStorePrice());
        dto.setName(item.getName());
        dto.setImage(
                item.getImage() == null ? null : MvcUriComponentsBuilder.fromMethodName(ItemController.class, "serveItemImage", item.getId()).build().toUriString()
        );
        return dto;
    }
}
