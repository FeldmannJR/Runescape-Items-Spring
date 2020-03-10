package dev.feldmann.runescapeitems.models;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long highAlchPrice;

    private Long lowAlchPrice;

    private Long storePrice;

    @ManyToOne
    private Image image;

    @OneToMany(mappedBy = "skill")
    private List<ItemRequiredSkill> requiredSkills;


}
