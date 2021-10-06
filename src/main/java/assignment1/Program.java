package assignment1;

import assignment1.models.Data;
import assignment1.models.Owner;
import assignment1.models.Pet;
import assignment1.xml.Marshalling_XML;

import java.util.ArrayList;
import java.util.Random;

public class Program {

    public static Data data;

    public static void main(String[] args) {
        data = new Data(new ArrayList<Owner>());

        seed(new Random(), 10);

        new Marshalling_XML().marshal(data);
    }

    public static void seed(Random r, int numRecords) {
        int ownerIdentity = 0,
            petIdentity = 0;

        int low = 9;
        int numOwners = r.nextInt(numRecords-low) + low;

        for (int i = 0; i < numOwners; i++) {
            Owner newOwner = new Owner(
                    ++ownerIdentity,
                    randomStringGenerator(r,50),
                    randomStringGenerator(r, 12),
                    randomStringGenerator(r, 1000));

            ArrayList<Pet> petList = new ArrayList<Pet>();
            for (int j = 0; j < r.nextInt(numOwners/2); j++) {
                Pet pet = new Pet(
                        ++petIdentity,
                        newOwner.getOwnerId(),
                        randomStringGenerator(r, 50),
                        randomStringGenerator(r, 10),
                        r.nextFloat(),
                        randomStringGenerator(r,10, 30),
                        randomStringGenerator(r, 100, 10000)
                );

                petList.add(pet);
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
}
