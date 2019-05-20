package Utils.Streams3;

public class UserLogged {
    private long id;
    private String name;
    private boolean logged;

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

    public boolean isLogged() {
        return this.logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String toString() {
        return "\n{id:\"" + this.id + "\", name:\"" + this.name + "\", logged:\"" + this.logged + "\"}";
    }
    public String toStringItem() {
        return "{id:\"" + this.id + "\", name:\"" + this.name + "\", logged:\"" + this.logged + "\"}";
    }
}
