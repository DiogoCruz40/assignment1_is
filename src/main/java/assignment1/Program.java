package assignment1;

import assignment1.models.Data;
import assignment1.models.Owner;
import assignment1.models.Pet;
import assignment1.xml.MarshallingXML;
import javax.xml.bind.JAXBException;
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
import java.util.logging.SimpleFormatter;

public class Program {

    private static Data data;
    private static Logger logger;
    private static final String loggerName = "ProgramLogger";

    public static void main(String[] args) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HH_mm_ss");
            logger = Logger.getLogger(loggerName);
            FileHandler fh;
            long seed = 0;
            int numOwners = 5;

            if (args.length > 0) {
                seed = Integer.parseInt(args[0]);
            } else {
                seed = System.currentTimeMillis();
            }

            //region Configure Logger

            Path path = Paths.get("./log");
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            // This block configure the logger with handler and formatter
            fh = new FileHandler(String.format("./log/%d_%d_%s.log", seed, numOwners, ZonedDateTime.now().format(formatter)));
            logger.addHandler(fh);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fh.setFormatter(simpleFormatter);

            // the following statement is used to log any messages
            logger.info("Program is stating...");

            //endregion Configure Logger

            Random r = new Random(seed);
            data = new Data(new ArrayList<Owner>(), new ArrayList<Pet>());
            seed(r, numOwners);

            String filepath = "./data/Ex2b.xml";

            MarshallingXML xml = new MarshallingXML();
            xml.marshal(data, filepath);
            Data deserializedData = xml.unmarshal(filepath);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An exception was thrown", e);
        }
    }

    public static void seed(Random r, int numRecords) {
        int ownerIdentity = 0,
            petIdentity = 0;

        int low = 1;
        int numOwners = r.nextInt(numRecords-low) + low;

        for (int i = 0; i < numOwners; i++) {
            Owner newOwner = new Owner(
                    String.valueOf(++ownerIdentity),
                    randomStringGenerator(r,50),
                    getRandomBigNumber(r, 12),
                    randomStringGenerator(r, 1000));

            ArrayList<Pet> petList = new ArrayList<Pet>();
            for (int j = 0; j < r.nextInt((int)Math.ceil((double)numOwners/2)); j++) {
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
