package Utils.Streams;

import java.time.LocalDate;

public class UserData {
    private long id;
    private String name;
    private LocalDate date;

    public UserData() {}

    public UserData(int id, String name, LocalDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public UserData(UserData item) {
        this.id = item.id;
        this.name = item.name;
        this.date = item.date;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "\n{id:\"" + this.id + "\", name:\"" + this.name + "\", date:\"" + this.date + "\"}";
    }
    public String toStringItem() {
        return "{id:\"" + this.id + "\", name:\"" + this.name + "\", date:\"" + this.date + "\"}";
    }
}
