package Utils.Annotations;

public class AnnotationTest {

    public int sum;

    @MyAnnotation(Name = "Brain", Value = 15)
    @Deprecated
    public int sum(int a, int b) {
        sum = a + b;
        return sum;
    }

}