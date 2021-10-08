package assignment1;

import assignment1.helpers.CsvBuilder;
import assignment1.helpers.CsvRowInfo;
import assignment1.helpers.InstrumentationAgent;
import assignment1.models.Data;
import assignment1.models.Owner;
import assignment1.models.Pet;
import assignment1.xml.MarshallingXML;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {

    private static Data data;
    private static Logger logger;
    private static final String loggerName = "ProgramLogger";
    private static CsvRowInfo csvRowInfo;

    public static void printObjectSize(Object object) {
        System.out.println("Object type: " + object.getClass() +
                ", size: " + InstrumentationAgent.getObjectSize(object) + " bytes");
    }

    public static void main(String[] args) throws IOException {
        try {
            //region Configure Logger And Results File
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HH_mm_ss");
            logger = Logger.getLogger(loggerName);
            FileHandler fh;

            Path resultsPath = Paths.get("./results");
            if (!Files.exists(resultsPath)) {
                Files.createDirectory(resultsPath);
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            Path filepath = Paths.get(String.format("./results/results_%s.csv", ZonedDateTime.now().format(dateFormatter)));

            if (Files.notExists(filepath)) {
                CsvBuilder.addNewLine(CsvRowInfo.getRowHeader());
            }

            Path logPath = Paths.get("./log");
            if (!Files.exists(logPath)) {
                Files.createDirectory(logPath);
            }

            // This block configure the logger with handler and formatter
//            fh = new FileHandler(String.format("./log/%d_%s.log", seed, ZonedDateTime.now().format(formatter)));
//            logger.addHandler(fh);
//            SimpleFormatter simpleFormatter = new SimpleFormatter();
//            fh.setFormatter(simpleFormatter);

            // the following statement is used to log any messages
            logger.info("Program is stating...");

            //endregion Configure Logger And Results File

            long seed = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                Run(seed, 100);
                seed = System.currentTimeMillis();
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An exception was thrown", e);
        }
    }

    public static void Run(long seed, int numMaxOwners) throws JAXBException, IOException {
        csvRowInfo = new CsvRowInfo();
        csvRowInfo.setSeed(seed);

        Random r = new Random(seed);
        data = new Data(new ArrayList<Owner>(), new ArrayList<Pet>());
        seed(r, numMaxOwners);

        //region Xml

        String filepath = String.format("./data/%d.xml", seed);

        MarshallingXML xml = new MarshallingXML();
        xml.marshal(data, filepath);
        //printObjectSize(data);
        xml.unmarshal(filepath);

        //endregion Xml

        //region Protocol Buffers



        //endregion Protocol Buffers

        CsvBuilder.addNewLine(csvRowInfo.getRowData());
    }

    public static CsvRowInfo getCsvRowInfo() {
        return csvRowInfo;
    }

    public static void seed(Random r, int numRecords) {
        int ownerIdentity = 0,
            petIdentity = 0,
            low = 1,
            numPets = 0;

        int numOwners = r.nextInt(numRecords-low) + low;
        csvRowInfo.setNumOwners(numOwners);

        for (int i = 0; i < numOwners; i++) {
            Owner newOwner = new Owner(
                    String.valueOf(++ownerIdentity),
                    randomStringGenerator(r,50),
                    getRandomBigNumber(r, 12),
                    randomStringGenerator(r, 1000));

            ArrayList<Pet> petList = new ArrayList<Pet>();
            int pets = r.nextInt((int)Math.ceil((double)numOwners/2));
            numPets += pets;
            for (int j = 0; j < pets; j++) {
                Pet pet = new Pet(
                        String.valueOf(++petIdentity),
                        newOwner,
                        randomStringGenerator(r, 50),
                        randomStringGenerator(r, 10),
                        r.nextFloat(),
                        randomStringGenerator(r,10, 30),
                        randomStringGenerator(r, 100, 10000)
                );

                petList.add(pet);
                data.addPet(pet);
            }

            newOwner.setPets(petList);

            data.addOwner(newOwner);
        }

        csvRowInfo.setNumPets(numPets);
    }

    public static String randomStringGenerator(Random r, int max) {
        return randomStringGenerator(r, 0, max);
    }

    public static String randomStringGenerator(Random r, int min, int max) {
        int length = r.nextInt(max - min) + min;

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        String generatedString = r.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static BigInteger getRandomBigNumber(Random r, int digCount) {
        StringBuilder sb = new StringBuilder(digCount);
        for(int i=0; i < digCount; i++)
            sb.append((char)('0' + r.nextInt(10)));
        return new BigInteger(sb.toString());
    }
}
