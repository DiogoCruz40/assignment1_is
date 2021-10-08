package assignment1.models;

import assignment1.helpers.TimeZoneAdaptor;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlAccessorType (XmlAccessType.FIELD)
public class Pet {

    //region Private Properties

    @XmlID
    @XmlAttribute
    private String petId;
    @XmlIDREF
    private Owner owner;
    @XmlAttribute
    private String name;
    private String gender;
    private float weight;
    @XmlJavaTypeAdapter(TimeZoneAdaptor.class)
    private LocalDateTime birthdate;
    private String description;

    //endregion Private Properties

    //region Constructor

    public Pet() {
    }

    public Pet(String petId, Owner owner, String name, String gender, float weight, String description) {
        this.petId = petId;
        this.owner = owner;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.birthdate = LocalDateTime.now();
        this.description = description;
    }


    //endregion Constructor

    //region Accessors

    public String getPetId() {
        return petId;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public float getWeight() {
        return weight;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public String getDescription() {
        return description;
    }


    //endregion Accessors
}
