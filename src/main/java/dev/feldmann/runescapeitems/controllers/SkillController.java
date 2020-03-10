package dev.feldmann.runescapeitems.controllers;


import dev.feldmann.runescapeitems.BaseController;
import dev.feldmann.runescapeitems.forms.CreateSkillForm;
import dev.feldmann.runescapeitems.models.Image;
import dev.feldmann.runescapeitems.models.Skill;
import dev.feldmann.runescapeitems.repositories.SkillRepository;
import dev.feldmann.runescapeitems.resources.SkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/skill")
public class SkillController extends BaseController {

    private SkillRepository skillRepository;

    @Autowired
    public SkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @PostMapping("/")
    public ResponseEntity<SkillDTO> createSkill(@Valid CreateSkillForm form) {
        Image image = imageService.uploadImage(form.getImage());
        Skill skill = new Skill();
        skill.setName(form.getName());
        skill.setImage(image);
        skillRepository.save(skill);
        SkillDTO dto = SkillDTO.from(skill);
        return ResponseEntity.created(
                MvcUriComponentsBuilder.fromMethodName(Skill.class, "findSkill", skill.getId())
                        .build()
                        .toUri()
        ).body(dto);
    }


    @GetMapping("/{skillId}")
    public ResponseEntity<? extends Object> findSkill(@Valid @PathVariable Long skillId) {
        Optional<Skill> skill = skillRepository.findById(skillId);
        if (skill.isPresent()) {
            return ResponseEntity.ok(SkillDTO.from(skill.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{skillId}/image")
    public ResponseEntity<? extends Object> serveSkillImage(@Valid @PathVariable Long skillId) {
        Optional<Skill> skill = skillRepository.findById(skillId);
        if (skill.isPresent()) {
            return this.serveImage(skill.get().getImage());
        }
        return ResponseEntity.notFound().build();
    }

}
