package assignment1.protobuf;

import assignment1.models.*;
import assignment1.models.protobuf.Owner_proto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

public class Marshalling_PROTO {



    public static void main(String[] args) throws IOException {
        Owner_proto.Builder owner = Owner_proto.newBuilder();
        Owner_proto.Pet_proto.Builder pet = Owner_proto.Pet_proto.newBuilder();

        owner.setOwnerId(12);
        owner.setTelephone(120302130);
        pet.setOwnerId(12);
        pet.setPetId(15);
        pet.setWeight((float) 15.22);
        owner.addPets(pet);

        try {
            owner.mergeFrom(new FileInputStream("proto.bin"));
        } catch (FileNotFoundException e) {
            System.out.println("proto.bin" + ": File not found.  Creating a new file.");
        }

        FileOutputStream output = new FileOutputStream("proto.bin",true);
        owner.build().writeTo(output);
        output.close();

        Owner_proto ownerread = Owner_proto.parseFrom(new FileInputStream("proto.bin"));

        System.out.println(ownerread.getPetsList());
    }
}

//protoc --java_out=../../ ./Owner_and_Pet.proto
