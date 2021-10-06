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

    public static void main(String[] args) {
        Owner_proto.Builder owner = Owner_proto.newBuilder();
        Owner_proto.Pet_proto.Builder pets = Owner_proto.Pet_proto.newBuilder();

    }
}
