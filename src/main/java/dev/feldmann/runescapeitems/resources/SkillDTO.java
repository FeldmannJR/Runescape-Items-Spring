package dev.feldmann.runescapeitems.resources;

import dev.feldmann.runescapeitems.controllers.ItemController;
import dev.feldmann.runescapeitems.controllers.SkillController;
import dev.feldmann.runescapeitems.models.Item;
import dev.feldmann.runescapeitems.models.Skill;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkillDTO {


    private Long id;
    private String name;
    private String image;


    public static SkillDTO from(Skill skill) {
        SkillDTO dto = new SkillDTO();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        dto.setImage(
                skill.getImage() == null ? null :
                        MvcUriComponentsBuilder.fromMethodName(SkillController.class, "serveSkillImage", skill.getId())
                                .build()
                                .toUriString()
        );
        return dto;
    }
}
