package dev.feldmann.runescapeitems.repositories;

import dev.feldmann.runescapeitems.models.Image;
import dev.feldmann.runescapeitems.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
