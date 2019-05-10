package Utils.Streams2;

import java.time.LocalDate;

public class FileTest {
    public String name;
    public LocalDate date;

    public FileTest() {}

    public  FileTest(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public String toString() {
        return "{name:\"" + this.name + "\", date:\"" + this.date + "\"}";
    }
}
