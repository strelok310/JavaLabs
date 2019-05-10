package Utils;

import java.time.LocalDateTime;

public class LoggedPerson extends Person {

    public boolean logged;

    public LoggedPerson() {}

    public LoggedPerson(String name, String surname, LocalDateTime date) {
        super(name, surname, date, false);
    }

    public LoggedPerson(String name, String surname, LocalDateTime date, boolean logged) {
        super(name, surname, date, false);
        this.logged = logged;
    }

    public LoggedPerson(LoggedPerson item) {
        super(item.firstName, item.lastName, item.createdAt);
        this.logged = item.logged;
    }

    public String toString() {
        return "{firstName:\"" + this.firstName + "\", lastName:\"" + this.lastName +
                "\", createdAt:\"" + this.createdAt.toString() + "\", logged:\"" + this.logged + "\"}";
    }

}
