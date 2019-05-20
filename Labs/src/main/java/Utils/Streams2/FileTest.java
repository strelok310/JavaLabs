package Utils.Streams2;

import java.time.LocalDate;

public class FileTest {
    private String name;
    private LocalDate date;

    public FileTest() {}

    public  FileTest(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String toString() {
        return "{name:\"" + this.name + "\", date:\"" + this.date + "\"}";
    }
}
