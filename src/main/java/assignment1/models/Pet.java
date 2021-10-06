package assignment1.models;

import javax.xml.bind.annotation.*;

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
    @XmlAttribute
    private String gender;
    @XmlAttribute
    private float weight;
    @XmlAttribute
    private String birthdate;
    @XmlAttribute
    private String description;

    //endregion Private Properties

    //region Constructor

    public Pet() {
    }

    public Pet(String petId, Owner owner, String name, String gender, float weight, String birthdate, String description) {
        this.petId = petId;
        this.owner = owner;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.birthdate = birthdate;
        this.description = description;
    }


    //endregion Constructor

    //region Accessors

    //endregion Accessors
}
