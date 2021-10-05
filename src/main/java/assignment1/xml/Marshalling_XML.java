package assignment1.xml;

import assignment1.models.Owner;
import assignment1.models.Pet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Marshalling_XML {

    public static void main(String[] args) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Owner.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders",
                    "<?xml version=\"1.0\" encoding=\"UTF-8\">");


//            Pets_Owners pets_owners = new Pets_Owners();
//            pets_owners.setPets_owner(new ArrayList<Pets_Owner>());
//            Pets_Owner pets_owner = new Pets_Owner();
//            pets_owner.setPets(new ArrayList<Pet>());
//
//            Pet pet = new Pet();
//            Pet pet2 = new Pet();
//            Pet pet3 = new Pet();
//            Owner owner = new Owner();
//
//            pet.setName("ola");
//            pet.setDescription("olaola");
//            pet2.setName("ola");
//            pet2.setDescription("olaola");
//            pet3.setName("ola");
//            pet3.setDescription("olaola");
//            owner.setName("chocolate");
//            owner.setAddress("RUa das flores");
//
//            pets_owner.setOwner(owner);
//            pets_owner.getPets().add(pet);
//            pets_owner.getPets().add(pet2);
//            pets_owner.getPets().add(pet3);
//            pets_owners.getPets_owner().add(pets_owner);


            // output to a xml file
            // jaxbMarshaller.marshal(turma, new File("Ex1.xml"));

            // output to console
            //jaxbMarshaller.marshal(pets_owners, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
