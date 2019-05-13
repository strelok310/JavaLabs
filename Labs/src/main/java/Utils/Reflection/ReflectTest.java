package Utils.Reflection;

import Utils.Reflection.ReflectionTestInterface;

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

    public void changeValue(int value) {
        this.value = value;
    }

    public void print() {
        System.out.println(this.output);
    }

    private void printHello() {
        System.out.println("Hello, " + this.output);
    }

    private boolean check(int value) {
        return logic = this.value > value;
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public void test(int a, int b) {
        System.out.printf("%s == %s: %s\n", a, b, a == b);
    }

}
