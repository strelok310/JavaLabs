package Utils;

import Utils.ReflectionTestInterface;

public class ReflectTest implements ReflectionTestInterface {

    public int value;
    public float precision;
    public String name;

    public final int RANDOM = 15;

    private long iterator;
    private boolean logic;
    private String output;

    protected int time;

    public ReflectTest() {
        //System.out.println("Public standard constructor");
        this.output = "Hello world!";
        this.name = "ASD";
        this.value = 10;
    }

    public ReflectTest(int value, String name) {
        //System.out.println("Public constructor");
        this.value = value;
        this.name = name;
        this.output = "Hello!";
    }

    ReflectTest(int value) {
        //System.out.println("constructor");
        this.value = value;
        this.output = "World!";
        this.name = "QWE";
    }

    private ReflectTest(Integer value, String name, String output) {
        this.value = value;
        this.output = output;
        this.name = name;
    }

    public void ChangeValue(int value) {
        this.value = value;
    }

    public void Print() {
        System.out.println(this.output);
    }

    private void PrintHello() {
        System.out.println("Hello, " + this.output);
    }

    private boolean Check(int value) {
        if(this.value > value) logic = true;
        else logic = false;
        return logic;
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public void test(int a, int b) {
        System.out.printf("%s == %s: %s\n", a, b, a == b);
    }

}
