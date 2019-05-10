package Utils;

import java.time.LocalDate;

public class UserData {
    public long id;
    public String name;
    public LocalDate date;

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

    public String toString() {
        return "\n{id:\"" + this.id + "\", name:\"" + this.name + "\", date:\"" + this.date + "\"}";
    }
}
