package Utils.Streams2;

public class MyPair {
    public int value;
    public String prefix;

    public MyPair() {}

    public MyPair(int value, String prefix) {
        this.value = value;
        this.prefix = prefix;
    }

    public MyPair(MyPair item) {
        this.value = item.value;
        this.prefix = item.prefix;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        else if(!MyPair.class.isAssignableFrom(obj.getClass())) return false;
        return this.value == ((MyPair)obj).value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.value);
    }

    @Override
    public String toString() {
        return this.prefix + "_" + this.value;
    }
}
