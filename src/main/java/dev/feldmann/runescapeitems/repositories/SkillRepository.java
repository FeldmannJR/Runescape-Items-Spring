package dev.feldmann.runescapeitems.repositories;

import dev.feldmann.runescapeitems.models.Image;
import dev.feldmann.runescapeitems.models.Skill;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends org.springframework.data.repository.CrudRepository<Skill, Long> {
}
