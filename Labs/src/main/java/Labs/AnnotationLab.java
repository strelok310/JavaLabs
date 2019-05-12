package Labs;

import Utils.Annotations.AnnotationTest;
import java.lang.annotation.Annotation;

public class AnnotationLab {
    public static void main(String args[]) throws Exception {
        try {
            Annotation[] annotations = AnnotationTest.class.getDeclaredMethod("sum",int.class,int.class).getDeclaredAnnotations();
            for (Annotation item : annotations) System.out.println(item);
        }
        catch(NoSuchMethodException e) { System.out.println(e); }
    }
}
