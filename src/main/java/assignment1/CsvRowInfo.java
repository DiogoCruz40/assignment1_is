package assignment1;

public class CsvRowInfo {
    private long seed;
    private int numOwners;
    private int numPets;
    private double xmlMarshallingTime;
    private double xmlUnmarshallingTime;

    public CsvRowInfo() {
    }

    public static String[] getRowHeader() {
        return new String[] {
                String.valueOf("seed"),
                String.valueOf("numOwners"),
                String.valueOf("numPets"),
                String.valueOf("xmlMarshallingTime(ms)"),
                String.valueOf("xmlUnmarshallingTime(ms)"),
        };
    }

    public String[] getRowData() {
        return new String[] {
                String.valueOf(this.seed),
                String.valueOf(this.numOwners),
                String.valueOf(this.numPets),
                String.valueOf(String.format("%.6f", this.xmlMarshallingTime)),
                String.valueOf(String.format("%.6f", this.xmlUnmarshallingTime)),
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
}
