package dev.feldmann.runescapeitems.models;


import javax.persistence.*;

@Entity
public class ItemRequiredSkill {

    @EmbeddedId
    private ItemSkillId id = new ItemSkillId();

    @ManyToOne
    @MapsId("itemId")
    private Item item;

    @ManyToOne
    @MapsId("skillId")
    private Skill skill;

    private Integer level = 1;


}
