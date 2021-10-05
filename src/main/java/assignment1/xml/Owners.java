package assignment1.xml;


import javax.xml.bind.annotation.*;


// order of the fields in XML
// @XmlType(propOrder = {"price", "name"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Owners {

    @XmlAttribute
    long id_owner;

    private String name_owner;
    private String birthdate;
    private Integer telephone;
    private String address;


    public long getId_owner() {
        return id_owner;
    }
    public void setId_owner(long id_owner) {
        this.id_owner = id_owner;
    }

    public String getName_owner() {
        return name_owner;
    }
    public void setName_owner(String name_owner) {
        this.name_owner = name_owner;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getTelephone() {
        return telephone;
    }
    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {return address;}
    public void setAddress(String address) {
        this.address = address;
    }

}
