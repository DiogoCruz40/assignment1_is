package assignment1.xml;

import assignment1.InstrumentationAgent;
import assignment1.models.Data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Logger;

public class MarshallingXML {

    private static Logger logger;
    private static final String loggerName = "ProgramLogger";
    private JAXBContext jaxbContext;

    public MarshallingXML() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(Data.class);
        this.logger = Logger.getLogger(loggerName);
    }

    public void marshal(Data data, String filepath) throws JAXBException {
        Marshaller jaxbMarshaller = this.jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
        jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders",
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        //FOR TESTS
//        Integer maxInteger = Integer.MAX_VALUE;
//        System.out.println("Object type: " + maxInteger.getClass() +
//                ", size: " + InstrumentationAgent.getObjectSize(maxInteger) + " bytes");

        // output to a xml file
        //logger.info("jaxbMarshaller.marshal executing... ");
        long start = System.nanoTime();
        jaxbMarshaller.marshal(data, new File(filepath));
        long end = System.nanoTime();
        logger.info(String.format("jaxbMarshaller.marshal end.\nelapsedTime: %f ms\nsize: ", (double) (end - start) / 1_000_000_000));

        // output to console
        //jaxbMarshaller.marshal(data, System.out);
    }
    
    public Data unmarshal(String filepath) throws JAXBException {
        Unmarshaller jaxbUnmarshaller = this.jaxbContext.createUnmarshaller();

        File file = new File(filepath);

        //logger.info("jaxbMarshaller.marshal executing... ");
        long start = System.nanoTime();
        Data data = (Data) jaxbUnmarshaller.unmarshal(file);
        long end = System.nanoTime();
        logger.info(String.format("jaxbMarshaller.unmarshal end.\nelapsedTime: %f ms\nsize: ", (double) (end - start) / 1_000_000_000));

        return data;
    }
}
