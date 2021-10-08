package assignment1;

import assignment1.helpers.CsvBuilder;
import assignment1.helpers.CsvRowInfo;
import assignment1.helpers.InstrumentationAgent;
import assignment1.models.Data;
import assignment1.models.Owner;
import assignment1.models.Pet;
import assignment1.models.protobuf.Data_proto;
import assignment1.xml.MarshallingXML;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.protobuf.Timestamp;

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

            Path dataPath = Paths.get("./data");
            if (!Files.exists(dataPath)) {
                Files.createDirectory(dataPath);
            }

            Path dataProtoPath = Paths.get("./dataProto");
            if (!Files.exists(dataProtoPath)) {
                Files.createDirectory(dataProtoPath);
            }

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
                Run(seed, 30);
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
        printObjectSize(data);
        xml.unmarshal(filepath);

        //endregion Xml

        //region Protocol Buffers

        String filepathProto = String.format("./dataProto/%d.bin", seed);
        Data_proto dataProtoBuf = ConvertDataToProtoClasses();

        FileOutputStream output = new FileOutputStream(filepathProto);
        long start = System.nanoTime();
        dataProtoBuf.writeTo(output);
        long end = System.nanoTime();
        output.close();
        long size = Files.size(Paths.get(filepath));
        logger.info(String.format("protoBuf.writeTo end.\nelapsedTime: %f ms\nsize: %d bytes", (double) (end - start) / 1_000_000_000, size));

        csvRowInfo.setProtobufWritingTime(start, end);
        csvRowInfo.setBytesBinFile(size);

        start = System.nanoTime();
        Data_proto dataProto = Data_proto.parseFrom(new FileInputStream(filepathProto));
        end = System.nanoTime();
        logger.info(String.format("protoBuf.parseFrom end.\nelapsedTime: %f ms\nsize: ", (double) (end - start) / 1_000_000_000));

        csvRowInfo.setProtobufReadingTime(start, end);

        //endregion Protocol Buffers

        CsvBuilder.addNewLine(csvRowInfo.getRowData());
    }

    public static Data_proto ConvertDataToProtoClasses (){
        Data_proto.Builder dataProto = Data_proto.newBuilder();

        for (Owner owner: data.getOwners()) {
            Data_proto.Owner_proto.Builder ownerProto = Data_proto.Owner_proto.newBuilder();

            ownerProto.setOwnerId(owner.getOwnerId());
            ownerProto.setName(owner.getName());

            Instant instant = owner.getBirthdate().toInstant(ZoneOffset.UTC);
            Timestamp timestamp = Timestamp.newBuilder()
                    .setSeconds(instant.getEpochSecond())
                    .setNanos(instant.getNano())
                    .build();
            ownerProto.setBirthdate(timestamp);

            ownerProto.setTelephone(owner.getTelephone().intValue());
            ownerProto.setAddress(owner.getAddress());

            for (Pet pet : owner.getPets()) {
                Data_proto.Owner_proto.Pet_proto.Builder petProto = Data_proto.Owner_proto.Pet_proto.newBuilder();

                petProto.setPetId(pet.getPetId());
                petProto.setName(pet.getName());
                petProto.setOwner(ownerProto);
                petProto.setGender(pet.getGender());
                petProto.setWeight(pet.getWeight());

                instant = pet.getBirthdate().toInstant(ZoneOffset.UTC);
                timestamp = Timestamp.newBuilder()
                        .setSeconds(instant.getEpochSecond())
                        .setNanos(instant.getNano())
                        .build();
                petProto.setBirthdate(timestamp);

                petProto.setDescription(pet.getDescription());

                ownerProto.addPets(petProto);
            }

            dataProto.addOwners(ownerProto);
        }

        return dataProto.build();
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
