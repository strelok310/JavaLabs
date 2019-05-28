package Utils.NIO;

import java.io.Serializable;

public class SerializableData implements Serializable {

    private int value;
    private boolean state;
    private String name;

    public SerializableData() {
        value = 5;
        state = true;
        name = "Simple";
    }

    public SerializableData(int value, boolean state, String name) {
        this.value = value;
        this.state = state;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Name: " + this.name + "\nValue: " + this.value + "\nState: " + this.state;
    }
}
