package assignment1.xml;

import assignment1.models.Data;
import assignment1.models.Owner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Marshalling_XML {

    public void marshal(Data data) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);

//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
//            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders",
//                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//
//            // output to a xml file
//            jaxbMarshaller.marshal(data, new File("Ex1.xml"));

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders",
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

            // output to a xml file
            jaxbMarshaller.marshal(data, new File("Ex1.xml"));

            // output to console
            //jaxbMarshaller.marshal(data, System.out);

            File file = new File("Ex1.xml");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Data o = (Data) jaxbUnmarshaller.unmarshal(file);
            System.out.println();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
