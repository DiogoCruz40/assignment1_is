package assignment1.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Data {
    //region Private Properties

    private ArrayList<Owner> owners;

    //endregion Private Properties

    //region Contructor

    public Data() {
        this.owners = new ArrayList<>();
    }

    public Data(ArrayList<Owner> owners) {
        this.owners = owners;
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
    //endregion Accessors
}
