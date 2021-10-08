package assignment1.helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CsvBuilder {

    private static int count = 0;

    public static void addNewLine(String[] values) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
        String filepath = String.format("./results/results_%s.csv", ZonedDateTime.now().format(formatter));

        FileWriter pw = new FileWriter(filepath,true);

        StringBuilder sb = new StringBuilder();
        sb.append(String.join(",",values));
        sb.append(System.lineSeparator());

        pw.write(sb.toString());

        pw.close();
    }

}