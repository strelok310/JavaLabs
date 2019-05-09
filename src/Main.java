import Annotations.AnnotationTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Ref;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String args[]) throws Exception {
        //MatrixTest();
        //ReflectionTest();
        //AnnotationTest();
        //CreateStream();

        MatrixLabs.main(args);
        ReflectionLabs.main(args);
        AnnotationLab.main(args);
        StreamLabs1.main(args);
        StreamLabs2.main(args);
    }
}
