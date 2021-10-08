package assignment1.protobufold;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Marshalling_PROTO {



    public static void main(String[] args) throws IOException {
//        Owner_proto.Builder owner = Owner_proto.newBuilder();
//        Owner_proto.Pet_proto.Builder pet = Owner_proto.Pet_proto.newBuilder();
//
//        owner.setOwnerId(12);
//        owner.setTelephone(120302130);
//        pet.setOwnerId(12);
//        pet.setPetId(15);
//        pet.setWeight((float) 15.22);
//        owner.addPets(pet);
//
//        try {
//            owner.mergeFrom(new FileInputStream("proto.bin"));
//        } catch (FileNotFoundException e) {
//            System.out.println("proto.bin" + ": File not found.  Creating a new file.");
//        }
//
//        FileOutputStream output = new FileOutputStream("proto.bin",true);
//        owner.build().writeTo(output);
//        output.close();
//
//        Owner_proto ownerread = Owner_proto.parseFrom(new FileInputStream("proto.bin"));
//
//        System.out.println(ownerread.getPetsList());
    }
}

//protoc --java_out=../../ ./Owner.proto
