package assignment1.helpers;

public class CsvRowInfo {
    private long seed;
    private int numOwners;
    private int numPets;
    private double xmlMarshallingTime;
    private double xmlUnmarshallingTime;
    private long bytesXmlFile;
    private double protobufWritingTime;
    private double protobufReadingTime;
    private long bytesBinFile;

    public CsvRowInfo() {
    }

    public static String[] getRowHeader() {
        return new String[] {
                String.valueOf("Seed"),
                String.valueOf("NumOwners"),
                String.valueOf("NumPets"),
                String.valueOf("Xml file Size (bytes)"),
                String.valueOf("Bin file Size (bytes)"),
                String.valueOf("Xml Marshalling (ms)"),
                String.valueOf("Protobuf Writing (ms)"),
                String.valueOf("Xml Unmarshalling (ms)"),
                String.valueOf("Protobuf Reading (ms)"),
        };
    }

    public String[] getRowData() {
        return new String[] {
                String.valueOf(this.seed),
                String.valueOf(this.numOwners),
                String.valueOf(this.numPets),
                String.valueOf(this.bytesXmlFile),
                String.valueOf(this.bytesBinFile),
                String.valueOf(String.format("%.6f", this.xmlMarshallingTime)),
                String.valueOf(String.format("%.6f", this.protobufWritingTime)),
                String.valueOf(String.format("%.6f", this.xmlUnmarshallingTime)),
                String.valueOf(String.format("%.6f", this.protobufReadingTime)),
        };
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public int getNumOwners() {
        return numOwners;
    }

    public void setNumOwners(int numOwners) {
        this.numOwners = numOwners;
    }

    public int getNumPets() {
        return numPets;
    }

    public void setNumPets(int numPets) {
        this.numPets = numPets;
    }

    public double getXmlMarshallingTime() {
        return xmlMarshallingTime;
    }

    public void setXmlMarshallingTime(long start, long end) {
        this.xmlMarshallingTime = (double) (end - start) / 1_000_000_000;
    }

    public double getXmlUnmarshallingTime() {
        return xmlUnmarshallingTime;
    }

    public void setXmlUnmarshallingTime(long start, long end) {
        this.xmlUnmarshallingTime = (double) (end - start) / 1_000_000_000;
    }

    public long getBytesXmlFile() {
        return bytesXmlFile;
    }

    public void setBytesXmlFile(long bytesXmlFile) {
        this.bytesXmlFile = bytesXmlFile;
    }

    public long getBytesBinFile() {
        return bytesBinFile;
    }

    public void setBytesBinFile(long bytesBinFile) {
        this.bytesBinFile = bytesBinFile;
    }

    public void setProtobufWritingTime(long start, long end) {
        this.protobufWritingTime = (double) (end - start) / 1_000_000_000;
    }
    public double getProtobufWritingTime() {
        return protobufWritingTime;
    }

    public void setProtobufReadingTime(long start, long end) {
        this.protobufReadingTime = (double) (end - start) / 1_000_000_000;
    }
    public double getProtobufReadingTime() {
        return protobufReadingTime;
    }
}
