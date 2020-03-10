package dev.feldmann.runescapeitems.repositories;

import dev.feldmann.runescapeitems.models.Image;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends org.springframework.data.repository.CrudRepository<Image, Long> {
}
