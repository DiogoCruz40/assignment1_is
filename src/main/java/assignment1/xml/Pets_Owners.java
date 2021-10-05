package assignment1.xml;


import javax.xml.bind.annotation.*;
import java.util.List;




@XmlRootElement(name = "Pets_Owners")
// order of the fields in XML
// @XmlType(propOrder = {"price", "name"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Pets_Owners {

    private List<Pets_Owner> pets_owner = null;

    public List<Pets_Owner> getPets_owner() {
        return pets_owner;
    }

    public void setPets_owner(List<Pets_Owner> pets_owner) {
        this.pets_owner = pets_owner;
    }




  /*
*/

}
