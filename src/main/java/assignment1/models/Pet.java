package assignment1.models;

import javax.xml.bind.annotation.*;

@XmlAccessorType (XmlAccessType.FIELD)
public class Pet {

    //region Private Properties

    @XmlAttribute
    private int petId;
    private int ownerId;
    private String name;
    private String gender;
    private float weight;
    private String birthdate;
    private String description;

    //endregion Private Properties

    //region Constructor

    public Pet() {
    }

    public Pet(int petId, int ownerId, String name, String gender, float weight, String birthdate, String description) {
        this.petId = petId;
        this.ownerId = ownerId;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.birthdate = birthdate;
        this.description = description;
    }


    //endregion Constructor

    //region Accessors

    public int getPetId() {
        return petId;
    }
    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getOwnerId() { return ownerId;}
    public void setOwnerId(int ownerId) {this.ownerId = ownerId;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //endregion Accessors
}
