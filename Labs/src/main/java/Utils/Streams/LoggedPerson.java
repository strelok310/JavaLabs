package Utils.Streams;

import java.time.LocalDateTime;

public class LoggedPerson extends Person {

    private boolean logged;

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

    public boolean isLogged() {
        return this.logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String toString() {
        return "\n{firstName:\"" + this.firstName + "\", lastName:\"" + this.lastName +
                "\", createdAt:\"" + this.createdAt.toString() + "\", logged:\"" + this.logged + "\"}";
    }

}
