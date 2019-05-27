package Labs;

import Utils.NIO.ShowDirectory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class NIOLabs {
    private static final String LINE = "\n==============================================================================================================\n";

    public static void main(String[] args) throws Exception {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
    }

    /**
     * Получить список всех файлов рекурсивно от заданного пути
     */

    static void task1() throws IOException {
        System.out.println(LINE);
        System.out.println("1) Show all files in the specified path recursively\n");

        Path path = Paths.get("D:/Tasks/MDE Framework");
        ShowDirectory showDirectory = new ShowDirectory();
        Files.walkFileTree(path, showDirectory);
    }

    /**
     * Получить список всех файлов рекурсивно от заданного пути с заданным форматом
     */

    static void task2() throws IOException {
        System.out.println(LINE);
        System.out.println("2) Show all files in the specified path and specified extension recursively\n");

        Path path = Paths.get("D:/Tasks/MDE Framework");
        ShowDirectory showDirectory = new ShowDirectory(".ttcn");
        Files.walkFileTree(path, showDirectory);
    }

    /**
     * Проверить, есть ли файл по заданному пути
     */

    static void task3() {
        System.out.println(LINE);
        System.out.println("3) Check whether file exist or not\n");

        Path path = Paths.get("D:/Chrome.txt");
        if (Files.exists(path)) System.out.println(path.getFileName().toString() + " exist");
        else System.out.println(path.getFileName().toString() + " doesn't exist");

        path = Paths.get("D:/Steam.rar");
        if (Files.exists(path)) System.out.println(path.getFileName().toString() + " exist");
        else System.out.println(path.getFileName().toString() + " doesn't exist");
    }

    /**
     * Проверить, файл или папка расположен по заданному пути?
     */

    static void task4() {
        System.out.println(LINE);
        System.out.println("4) Check path whether it is a file or it is a folder\n");

        Path path = Paths.get("D:/Chrome.txt");
        if(Files.isDirectory(path)) System.out.println(path.getFileName().toString() + " folder");
        else if(!Files.exists(path)) System.out.println(path.getFileName() + "doesn't exist");
        else System.out.println(path.getFileName().toString() + " file");

        path = Paths.get("D:/Info");
        if(Files.isDirectory(path)) System.out.println(path.getFileName().toString() + " folder");
        else if(!Files.exists(path)) System.out.println(path.getFileName() + "doesn't exist");
        else System.out.println(path.getFileName().toString() + " file");
    }

    /**
     * Получить размер файла в килобайтах
     */

    static void task5() throws IOException {
        System.out.println(LINE);
        System.out.println("5) Get file size\n");

        Path path = Paths.get("D:/Tasks/MDE Framework/MDE_Framework_PA15.zip");
        if(!Files.exists(path)) System.out.println(path.getFileName() + "doesn't exist");
        else System.out.println(Math.round(Files.size(path) / 1024.0) + " Kb");
    }

    /**
     * Прочитать файл в массив байт
     */

    static void task6() throws IOException, URISyntaxException {
        System.out.println(LINE);
        System.out.println("6) Read file as byte array\n");

        Path path = Paths.get(ClassLoader.getSystemResource("class.txt").toURI());
        byte[] mas = Files.readAllBytes(path);
        System.out.println(mas.length + " bytes have been read");
    }

    /**
     * Прочитать файл в массив строк
     */

    static void task7() throws IOException, URISyntaxException {
        System.out.println(LINE);
        System.out.println("7) Read file as lines array\n");

        Path path = Paths.get(ClassLoader.getSystemResource("class.txt").toURI());
        List<String> mas = Files.readAllLines(path);
        System.out.println(mas.size() + " lines have been read");
    }



}




































