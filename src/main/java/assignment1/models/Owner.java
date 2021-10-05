package assignment1.models;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

// order of the fields in XML
// @XmlType(propOrder = {"price", "name"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Owner {

    //region Private Properties

    @XmlAttribute
    private int ownerId;
    private String name;
    private LocalDateTime birthdate;
    private String telephone;
    private String address;
    private ArrayList<Pet> pets;

    //endregion Private Properties

    //region Constructor

    public Owner() {
        this.pets = new ArrayList<>();
    }

    public Owner(int ownerId, String name, LocalDateTime birthdate, String telephone, String address) {
        this.ownerId = ownerId;
        this.name = name;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.address = address;
        this.pets = new ArrayList<>();
    }

    //endregion Constructor

    //region Accessors

    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Pet> getPets() { return pets; }
    public void AddPet(Pet pet) { this.pets.add(pet); }

    //endregion Accessors

}
