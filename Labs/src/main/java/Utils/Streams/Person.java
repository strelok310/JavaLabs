package Utils.Streams;

import java.time.LocalDateTime;

public class Person {
    public String firstName;
    public String lastName;
    public LocalDateTime createdAt;
    public boolean logged;

    public Person() {}

    public Person(String name, String surname, LocalDateTime date) {
        this(name, surname, date, false);
    }

    public Person(String name, String surname, LocalDateTime date, boolean logged) {
        this.firstName = name;
        this.lastName = surname;
        this.createdAt = date;
        this.logged = logged;
    }

    public Person(Person item) {
        this.firstName = item.firstName;
        this.lastName = item.lastName;
        this.createdAt = item.createdAt;
    }

    public String toString() {
        return "\n{firstName:\"" + this.firstName + "\", lastName:\"" + this.lastName +
                "\", createdAt:\"" + this.createdAt.toString() + "\"}";
    }
}
