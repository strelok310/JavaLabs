package Utils.Streams3;

public class UserLogged {
    public long id;
    public String name;
    public boolean logged;

    public UserLogged() {}

    public UserLogged(int id, String name, boolean logged) {
        this.id = id;
        this.name = name;
        this.logged = logged;
    }

    public UserLogged(UserLogged item) {
        this.id = item.id;
        this.name = item.name;
        this.logged =  item.logged;
    }

    public String toString() {
        return "\n{id:\"" + this.id + "\", name:\"" + this.name + "\", logged:\"" + this.logged + "\"}";
    }
    public String toStringItem() {
        return "{id:\"" + this.id + "\", name:\"" + this.name + "\", logged:\"" + this.logged + "\"}";
    }
}
