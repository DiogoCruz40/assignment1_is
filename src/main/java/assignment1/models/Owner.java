package assignment1.models;

import assignment1.TimeZoneAdaptor;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Owner {

    //region Private Properties

    @XmlID
    @XmlAttribute
    private String ownerId;
    @XmlAttribute
    private String name;
    @XmlJavaTypeAdapter(TimeZoneAdaptor.class)
    @XmlAttribute
    private ZonedDateTime birthdate;
    @XmlAttribute
    private BigInteger telephone;
    @XmlAttribute
    private String address;
    @XmlIDREF
    @XmlElement(name="pet")
    private ArrayList<Pet> pets;

    //endregion Private Properties

    //region Constructor

    public Owner() {
        this.pets = new ArrayList<>();
        this.birthdate = ZonedDateTime.now();
    }

    public Owner(String ownerId, String name, BigInteger telephone, String address) {
        this.ownerId = ownerId;
        this.name = name;
        this.telephone = telephone;
        this.address = address;

        this.pets = new ArrayList<>();
        this.birthdate = ZonedDateTime.now();
    }

    //endregion Constructor

    //region Accessors

    public void setPets(ArrayList pets) { this.pets = pets; }

    //endregion Accessors

}
