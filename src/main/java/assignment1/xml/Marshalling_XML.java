package assignment1.xml;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;

public class Marshalling_XML {

    public static void main(String[] args) {

        JAXBContext jaxbContext = null;
        try {


            jaxbContext = JAXBContext.newInstance(Pets_Owners.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();


            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders",
                    "<?xml version=\"1.0\" encoding=\"UTF-8\">");


            Pets_Owners pets_owners = new Pets_Owners();
            pets_owners.setPets_owner(new ArrayList<Pets_Owner>());
            Pets_Owner pets_owner = new Pets_Owner();
            pets_owner.setPets(new ArrayList<Pets>());

            Pets pet = new Pets();
            Pets pet2 = new Pets();
            Pets pet3 = new Pets();
            Owners owner = new Owners();

            pet.setName_pet("ola");
            pet.setDescription("olaola");
            pet2.setName_pet("ola");
            pet2.setDescription("olaola");
            pet3.setName_pet("ola");
            pet3.setDescription("olaola");
            owner.setName_owner("chocolate");
            owner.setAddress("RUa das flores");

            pets_owner.setOwner(owner);
            pets_owner.getPets().add(pet);
            pets_owner.getPets().add(pet2);
            pets_owner.getPets().add(pet3);
            pets_owners.getPets_owner().add(pets_owner);


            // output to a xml file
          // jaxbMarshaller.marshal(turma, new File("Ex1.xml"));

            // output to console
             jaxbMarshaller.marshal(pets_owners, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
