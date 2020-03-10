package dev.feldmann.runescapeitems.models;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ItemSkillId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long skillId;
    private Long itemId;


}
