package assignment1.xml;

import javax.xml.bind.annotation.*;


@XmlAccessorType (XmlAccessType.FIELD)
public class Pets {

    @XmlAttribute
     long id_pet;



    private Long owner_id;
    private String name_pet;
    private String gender;
    private Double weight;
    private String birthdate;
    private String description;

    public long getId_pet() {
        return id_pet;
    }
    public void setId_pet(long id_pet) {
        this.id_pet = id_pet;
    }


    public Long getOwner_id() { return  owner_id;}
    public void setOwner_id(Long owner_id) {this.owner_id = owner_id;}

    public String getName_pet() {
        return name_pet;
    }
    public void setName_pet(String name_pet) {
        this.name_pet = name_pet;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
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




}
