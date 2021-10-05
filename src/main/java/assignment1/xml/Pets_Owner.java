package assignment1.xml;


import javax.xml.bind.annotation.*;
import java.util.List;





// order of the fields in XML
// @XmlType(propOrder = {"price", "name"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Pets_Owner {



    private List<Pets> pet = null;
    private Owners owner = null;

    public List<Pets> getPets() {
        return pet;
    }
    public void setPets(List<Pets> pet) {
        this.pet = pet;
    }

    public Owners getOwner() { return  owner;}
    public  void setOwner(Owners owner) { this.owner = owner;}

  /*
*/

}
