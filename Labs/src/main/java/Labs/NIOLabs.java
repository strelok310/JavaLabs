package Labs;

import Utils.NIO.ShowDirectory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        task8();
        task9();
        task10();
        task11();
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

    /**
     * Сымитировать ошибку в процессе чтения файла (выбросить исключение). Корректно освободить все ресурсы.
     */

    static void task8() throws IOException, URISyntaxException {
        System.out.println(LINE);
        System.out.println("8) Throw exception during reading file and free resources\n");

        Path path = Paths.get(ClassLoader.getSystemResource("class.txt").toURI());
        try(BufferedReader bf = Files.newBufferedReader(path)) {
            String str = null;
            while((str = bf.readLine()) != null) {
                System.out.println("String: " + str);
                throw new IOException("Test IO error");
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Добавить строку в конец файла
     */

    static void task9() throws IOException {
        System.out.println(LINE);
        System.out.println("9) Add line in the end of file\n");

        Path path = Paths.get("D:/tmp_time.txt");
        if(!Files.exists(path)) Files.createFile(path);

        //Variant 1
        Files.write(path, ("\n" + LocalDateTime.now().toString()).getBytes(), StandardOpenOption.APPEND);

        //Variant 2
        try(BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bw.newLine();
            String str = LocalDateTime.now().toString();
            bw.write(str, 0, str.length());
        }
        catch (IOException e) {
            throw e;
        }

    }

    /**
     * Записать в массив первые три строки из файла
     */

    static void task10() throws IOException, URISyntaxException {
        System.out.println(LINE);
        System.out.println("10) Read first three lines from file\n");

        ArrayList<String> list = new ArrayList<>();
        Path path = Paths.get(ClassLoader.getSystemResource("stream_example.txt").toURI());
        try(BufferedReader br = Files.newBufferedReader(path)) {
            while (list.size() < 3) list.add(br.readLine());
        }
        catch (IOException e) {
            throw e;
        }

        System.out.println(list);
    }

    /**
     * Записать в массив последние две строки из файла
     */

    static void task11() throws IOException, URISyntaxException {
        System.out.println(LINE);
        System.out.println("11) Read last two lines from file\n");

        ArrayList<String> list = new ArrayList<>();
        Path path = Paths.get(ClassLoader.getSystemResource("stream_example.txt").toURI());
        long count = Files.lines(path).count();
        list.addAll(Files.lines(path).skip(count-2).collect(Collectors.toList()));
        System.out.println(list);
    }

}




































