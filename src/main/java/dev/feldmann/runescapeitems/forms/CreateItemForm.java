package dev.feldmann.runescapeitems.forms;

import dev.feldmann.runescapeitems.validation.annotations.ValidImage;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateItemForm {
    @NotBlank
    private String name;
    @NotNull
    private Long highAlchPrice;
    @NotNull
    private Long lowAlchPrice;
    @NotNull
    private Long storePrice;
    @ValidImage(ratio = 1)
    @NonNull
    private MultipartFile image;
}
