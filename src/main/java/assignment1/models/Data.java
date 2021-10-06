package assignment1.models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    //region Private Properties

    @XmlElement(name="owner")
    private ArrayList<Owner> owners;

    @XmlElement(name="pet")
    private ArrayList<Pet> pets;

    //endregion Private Properties

    //region Contructor

    public Data() {
        this.owners = new ArrayList<>();
        this.pets = new ArrayList<>();
    }

    public Data(ArrayList<Owner> owners, ArrayList<Pet> pets) {
        this.owners = owners;
        this.pets = pets;
    }

    //endregion Contructor

    //region Accessors

    public ArrayList<Owner> getOwners() {
        return owners;
    }

    public void setOwners(ArrayList<Owner> owners) {
        this.owners = owners;
    }

    public void addOwner(Owner newOwner) {
        this.owners.add(newOwner);
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet newPet) {
        this.pets.add(newPet);
    }

    //endregion Accessors
}
