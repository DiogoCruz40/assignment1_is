package assignment1.models;

import assignment1.helpers.TimeZoneAdaptor;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Owner implements Serializable {

    //region Private Properties

    @XmlID
    @XmlAttribute
    private String ownerId;
    @XmlAttribute
    private String name;
    @XmlJavaTypeAdapter(TimeZoneAdaptor.class)
    private LocalDateTime birthdate;
    private BigInteger telephone;
    private String address;
//    @XmlIDREF
    @XmlElement(name="pet")
    private ArrayList<Pet> pets;

    //endregion Private Properties

    //region Constructor

    public Owner() {
        this.pets = new ArrayList<>();
        this.birthdate = LocalDateTime.now();
    }

    public Owner(String ownerId, String name, BigInteger telephone, String address) {
        this.ownerId = ownerId;
        this.name = name;
        this.telephone = telephone;
        this.address = address;

        this.pets = new ArrayList<>();
        this.birthdate = LocalDateTime.now();
    }

    //endregion Constructor

    //region Accessors

    public void setPets(ArrayList pets) { this.pets = pets; }

    public String getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public BigInteger getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }
    //endregion Accessors

}
