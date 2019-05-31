package Labs;

import Utils.NIO.*;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class NIOLabs {
    private static final String LINE = "\n==============================================================================================================\n";

    public static void main(String[] args) throws Exception {
        task1("D:/Tasks/MDE Framework"); //Show directory
        task2("D:/Tasks/MDE Framework", ".ttcn"); //Show all files with extension
        task3(new String[] {"D:/Chrome.txt",
                            "D:/Steam.rar"});   //Check files
        task4(new String[] {"D:/Chrome.txt",
                            "D:/Info"});        //Check file/directory
        task5("D:/Tasks/MDE Framework/MDE_Framework_PA15.zip"); //File
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19("D:/java_nio_tmp/"); //Path to save zip and unzip files
        task20();
        task21();
        task22();
        task23("D:/java_nio_tmp/image.jpg"); //Path where image should be saved
        task24("D:/java_nio_tmp/image2.jpg"); //Path where image should be saved
    }

    /**
     * Получить список всех файлов рекурсивно от заданного пути
     */

    static void task1(String directory) throws IOException {
        System.out.println(LINE);
        System.out.println("1) Show all files in the specified path recursively\n");

        Path path = Paths.get(directory);
        ShowDirectory showDirectory = new ShowDirectory();
        Files.walkFileTree(path, showDirectory);
    }

    /**
     * Получить список всех файлов рекурсивно от заданного пути с заданным форматом
     */

    static void task2(String directory, String extension) throws IOException {
        System.out.println(LINE);
        System.out.println("2) Show all files in the specified path and specified extension recursively\n");

        Path path = Paths.get(directory);
        ShowDirectory showDirectory = new ShowDirectory(extension);
        Files.walkFileTree(path, showDirectory);
    }

    /**
     * Проверить, есть ли файл по заданному пути
     */

    static void task3(String[] files) {
        System.out.println(LINE);
        System.out.println("3) Check whether file exist or not\n");

        for(String file : files) {
            Path path = Paths.get(file);
            if (Files.exists(path)) System.out.println(path.getFileName().toString() + " exist");
            else System.out.println(path.getFileName().toString() + " doesn't exist");
        }
    }

    /**
     * Проверить, файл или папка расположен по заданному пути?
     */

    static void task4(String[] files) {
        System.out.println(LINE);
        System.out.println("4) Check path whether it is a file or it is a folder\n");

        for(String file : files) {
            Path path = Paths.get(file);
            if(Files.isDirectory(path)) System.out.println(path.getFileName().toString() + " folder");
            else if(!Files.exists(path)) System.out.println(path.getFileName() + "doesn't exist");
            else System.out.println(path.getFileName().toString() + " file");
        }
    }

    /**
     * Получить размер файла в килобайтах
     */

    static void task5(String file) throws IOException {
        System.out.println(LINE);
        System.out.println("5) Get file size\n");

        Path path = Paths.get(file);
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

    static void task9() throws IOException, URISyntaxException {
        System.out.println(LINE);
        System.out.println("9) Add line in the end of file\n");

        //Path path = Paths.get("D:/tmp_time.txt");
        //if(!Files.exists(path)) Files.createFile(path);
        Path path = Paths.get(ClassLoader.getSystemResource("nio_time.txt").toURI());

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

    /**
     * Найти самое длинное слово в файле
     */

    static void task12() throws IOException, URISyntaxException {
        System.out.println(LINE);
        System.out.println("12) Find the longest word in the file\n");

        Path path = Paths.get(ClassLoader.getSystemResource("wordCount.txt").toURI());
        String str = Files.lines(path)
                          .flatMap(x -> Arrays.stream(x.split("\\s|\\,|\\.|\\?|\\!|\\-\\:")))
                          .sorted((x,y) -> y.length() - x.length())
                          .findFirst().orElse(null);

        if(str != null) System.out.println("The longest word: " + str);
        else System.out.println("No words found");
    }

    /**
     * Найти самое частое слово в файле
     */

    static void task13() throws IOException, URISyntaxException {
        System.out.println(LINE);
        System.out.println("13) Find the most frequent word in the file\n");

        Path path = Paths.get(ClassLoader.getSystemResource("wordCount.txt").toURI());
        Map.Entry<String, Long> word = Files.lines(path)
              .flatMap((x) -> Arrays.stream(x.split("\\s|\\,|\\.|\\?|\\!|\\-\\:")))
              .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
              .entrySet()
              .stream()
              .sorted((x,y) -> Math.toIntExact(y.getValue() - x.getValue()))
              .findFirst().orElse(null);

        if(word != null) System.out.println("Word: " + word.getKey());
        else System.out.println("No words found");
    }

    /**
     * Преобразовать ByteArrayInputStream в ByteArrayOutputStream и обратно
     */

    static void task14() {
        System.out.println(LINE);
        System.out.println("14) Convert ByteArrayInputStream to ByteArrayOutputStream and vice versa\n");

        ByteArrayInputStream input = new ByteArrayInputStream(new byte[] {1,2,3,4,5});
        byte[] buf = new byte[input.available()];
        input.read(buf, 0, input.available());
        System.out.println("Input stream: " + Arrays.toString(buf));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(buf, 0, buf.length);
        System.out.println("Output stream: " + Arrays.toString(output.toByteArray()));

        input = new ByteArrayInputStream(output.toByteArray());
        buf = new byte[input.available()];
        input.read(buf, 0, input.available());
        System.out.println("Input stream: " + Arrays.toString(buf));
    }

    /**
     * Преобразовать BufferedInputStream в BufferedOutputStream и обратно
     */

    static void task15() throws IOException {
        System.out.println(LINE);
        System.out.println("15) Convert BufferedInputStream to BufferedOutputStream  and vice versa\n");

        byte[] buf;

        ByteArrayInputStream byteInput = new ByteArrayInputStream(new byte[] {1,2,3,4,5});
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

        //Convert input to output
        try(BufferedInputStream input = new BufferedInputStream(byteInput);
            BufferedOutputStream output = new BufferedOutputStream(byteOutput)) {

            buf = new byte[input.available()];
            input.read(buf);
            System.out.println("Input stream: " + Arrays.toString(buf));

            output.write(buf);
        }
        catch (IOException e) {
            throw e;
        }

        System.out.println("Output stream: " + Arrays.toString(byteOutput.toByteArray()));

        //Convert output to input
        byteInput = new ByteArrayInputStream(byteOutput.toByteArray());
        try(BufferedInputStream input = new BufferedInputStream(byteInput)) {

            buf = new byte[input.available()];
            input.read(buf);
            System.out.println("Input stream: " + Arrays.toString(buf));
        }
        catch (IOException e) {
            throw e;
        }
    }

    /**
     * Использовать PrintStream для вывода в файл
     */

    static void task16() throws IOException {
        System.out.println(LINE);
        System.out.println("16) Write to file by PrintStream\n");

        //try(PrintStream output = new PrintStream("D:/tmp_task16.txt")) {
        try(PrintStream output = new PrintStream(ClassLoader.getSystemResource("nio_print.txt").getPath())) {
            output.println("Hello world!");
        }
        catch (IOException e) {
            throw e;
        }
    }

    /**
     * Использовать DataInputStream для записи примитивов в файл. Прочитать с использованием DataOutputStream
     */

    static void task17() throws IOException {
        System.out.println(LINE);
        System.out.println("17) Use DataInputStream/DataOutputStream to read/write primitives\n");

        try(FileOutputStream file = new FileOutputStream(ClassLoader.getSystemResource("nio_primitives.bin").getPath());
            DataOutputStream output = new DataOutputStream(file)) {

            output.writeBoolean(true);
            output.writeByte(0xf);
            output.writeChar('C');
            output.writeShort(9);
            output.writeInt(36);
            output.writeLong(26L);
            output.writeFloat(1.3f);
            output.writeDouble(9.11);
            output.writeUTF("Hello world!");
        }
        catch (IOException e) {
            throw e;
        }

        try(FileInputStream file = new FileInputStream(ClassLoader.getSystemResource("nio_primitives.bin").getPath());
            DataInputStream input = new DataInputStream(file)) {

            boolean a = input.readBoolean();    System.out.println("Boolean: " + a);
            byte b = input.readByte();          System.out.println("Byte: " + b);
            char c = input.readChar();          System.out.println("Char: " + c);
            short d = input.readShort();        System.out.println("Short: " + d);
            int f = input.readInt();            System.out.println("Integer: " + f);
            long g = input.readLong();          System.out.println("Long: " + g);
            float h = input.readFloat();        System.out.println("Float: " + h);
            double i = input.readDouble();      System.out.println("Double: " + i);
            String str = input.readUTF();       System.out.println("String: " + str);
        }
        catch (IOException e) {
            throw e;
        }
    }

    /**
     * Использовать ObjectInputStream для сериализации. Десериализовать серилизованный объект.
     */

    static void task18() throws IOException, ClassNotFoundException {
        System.out.println(LINE);
        System.out.println("18) Serialize and deserialize object\n");

        SerializableData data = new SerializableData(6, true, "Hello");
        SerializableData newData;

        try(FileOutputStream file = new FileOutputStream(ClassLoader.getSystemResource("nio_data.bin").getPath());
            ObjectOutputStream output = new ObjectOutputStream(file)) {

            output.writeObject(data);
        }
        catch (IOException e) {
            throw e;
        }

        try(FileInputStream file = new FileInputStream(ClassLoader.getSystemResource("nio_data.bin").getPath());
            ObjectInputStream input = new ObjectInputStream(file)) {

            newData = (SerializableData) input.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            throw e;
        }

        System.out.println(newData);
    }

    /**
     * Заархивировать в zip несколько файлов. Разархивировать файлы из zip.
     * Расчитать размер каждого файла в архиве
     */

    static void task19(String path) throws IOException {
        System.out.println(LINE);
        System.out.println("19) Compress/decompress some files to/from zip\n");

        if(!path.endsWith("/") || !path.endsWith("\\")) path = path + "/";
        Path pathDir = Paths.get(path);
        if(!Files.exists(pathDir)) Files.createDirectory(pathDir);
        URL filePath;

        String[] files = {
            "class.txt",
            "matrix_A.txt",
            "matrix_B.txt",
            "nio_data.bin",
            "nio_primitives.bin",
            "nio_print.txt",
            "nio_time.txt",
            "stream_example.txt",
            "wordCount.txt"
        };

        System.out.println("Compressing files:");
        try(FileOutputStream zipFile = new FileOutputStream(path + "tmp.zip");
            ZipOutputStream zip = new ZipOutputStream(zipFile)) {

            for(String item : files) {
                if((filePath = ClassLoader.getSystemResource(item)) != null) {
                    try( FileInputStream file = new FileInputStream(filePath.getPath()) ) {
                        byte[] data = new byte[file.available()];
                        file.read(data);

                        zip.putNextEntry(new ZipEntry(item));
                        zip.write(data);
                        zip.closeEntry();

                        System.out.println("\t" + item + " added to zip");
                    }
                }
            }
        }
        System.out.println("Compression completed");

        System.out.println();

        System.out.println("Extracting files:");
        try(FileInputStream zipFile = new FileInputStream(path + "tmp.zip");
            ZipInputStream zip = new ZipInputStream(zipFile)) {

            ZipEntry entry;
            while((entry = zip.getNextEntry()) != null) {
                try(FileOutputStream file = new FileOutputStream(path + entry.getName())) {
                    int data;
                    while((data = zip.read()) != -1) file.write(data);
                }

                long size = entry.getSize();
                long csize = entry.getCompressedSize();
                double compression = Double.valueOf(csize) / size * 100;
                System.out.format("\t%-20s \t%10d | %-10d Bytes (%.1f %%)\n", entry.getName(), size, csize, compression);

                zip.closeEntry();
            }
        }
        System.out.println("Extraction completed");

        System.out.println("\nContent of zip file:");
        ZipFile zf = new ZipFile(path + "tmp.zip");
        zf.stream().forEach(x -> {
            long size = x.getSize();
            long csize = x.getCompressedSize();
            double compression = Double.valueOf(csize) / size * 100;
            System.out.format("\t%-20s \t%10d | %-10d Bytes (%.1f %%)\n", x.getName(), size, csize, compression);
        });
    }

    /**
     * Считать последние 1000 символов из файла
     */

    static void task20() throws IOException, URISyntaxException {
        System.out.println(LINE);
        System.out.println("20) Read last 1000 characters from file\n");

        Path path = Paths.get(ClassLoader.getSystemResource("nio_characters.txt").toURI());
        long size = Files.size(path);
        System.out.println(size + " Bytes");

        try(FileReader file = new FileReader(ClassLoader.getSystemResource("nio_characters.txt").getPath())) {
            size = file.skip(size);
        }
        System.out.println(size + " characters\n");

        char[] symbols;
        try(FileReader file = new FileReader(ClassLoader.getSystemResource("nio_characters.txt").getPath())) {
            if(size >= 1000) {
                symbols = new char[1000];
                file.skip(size - 1000);
                file.read(symbols, 0, 1000);
            }
            else {
                symbols = new char[Math.toIntExact(size)];
                file.read(symbols);
            }
        }

        System.out.println(symbols);
    }

    /**
     * Используя сокет реализовать сервер, который принимает на вход TimeZone, а возвращает текущее время
     * в заданной TimeZone. Реализовать клиента, который будет отправлять свою либо случайную TimeZone на сервер.
     */

    static void task21() throws IOException {
        System.out.println(LINE);
        System.out.println("21) Create server that receives TimeZone and send current time for it\n");

        String[] zones = {
                "UTC",
                "Europe/Minsk",
                "Asia/Tokyo",
                "Asia/Bangkok",
                "Asia/Shanghai",
                "America/New_York",
                "Europe/Berlin"
        };

        Thread clientThread = new TimeClientThread("Client", zones);
        clientThread.start();

        //Server side begin

        int count = zones.length;
        try(ServerSocket server = new ServerSocket(1503)) {
            while(count != 0) {
                new TimeServerThread("Client_" + LocalTime.now(), server.accept()).run();
                count--;
            }
        }

        //Server side end

        try {
            clientThread.join();
        }
        catch (InterruptedException e) {
            System.out.println(clientThread.getName() + " finished");
        }
    }

    /**
     * Переведите текст из ANSII в UTF-8, затем ASCII, затем в кодировку по умолчанию
     */

    static void task22() {
        System.out.println(LINE);
        System.out.println("22) Change character encoding\n");

        String str = "Hello world! Привет";
        System.out.println("Original: \"" + str + "\"");
        System.out.println(Arrays.toString(str.getBytes()));

        byte[] byteASCII = str.getBytes(StandardCharsets.US_ASCII);
        String strASCII = new String(byteASCII, StandardCharsets.US_ASCII);
        System.out.println("\nASCII: \"" + strASCII + "\"");
        System.out.println(Arrays.toString(byteASCII));

        byte[] byteUTF8 = strASCII.getBytes(StandardCharsets.UTF_8);
        String strUTF8 = new String(byteUTF8, StandardCharsets.UTF_8);
        System.out.println("\nASCII to UTF-8: \"" + strUTF8 + "\"");
        System.out.println(Arrays.toString(byteUTF8));

        byte[] byteASCII2 = strUTF8.getBytes(StandardCharsets.US_ASCII);
        String strASCII2 = new String(byteASCII2, StandardCharsets.US_ASCII);
        System.out.println("\nUTF-8 to ASCII: \"" + strASCII2 + "\"");
        System.out.println(Arrays.toString(byteASCII2));

        byte[] byteDefault = strASCII2.getBytes(Charset.defaultCharset());
        String strDefault = new String(byteDefault, Charset.defaultCharset());
        System.out.println("\nASCII to default: \"" + strDefault + "\"");
        System.out.println(Arrays.toString(byteDefault));
    }

    /**
     * Откройте картинку как бинарный файл, переведите ее в Base64. Отправьте по сокету.
     * Раскодируйте из Base64 и сохраните на диск. Убедитесь, что картинка не повреждена.
     */

    static void task23(String filePath) throws IOException {
        System.out.println(LINE);
        System.out.println("23) Encode/Decode image to/from Base64\n");

        Thread clientThread = new PictureClientThread("Client");
        clientThread.start();

        //Server side begin

        try(ServerSocket server = new ServerSocket(1503)) {
            new PictureServerThread("Client_" + LocalTime.now(), server.accept(), filePath).run();
        }

        //Server side end

        try {
            clientThread.join();
        }
        catch (InterruptedException e) {
            System.out.println(clientThread.getName() + " finished");
        }
    }

    /**
     * В предыдущем задании вместо сокета отправьте данные коллеге, у которого кодировка по умолчанию
     * отличается от вашей (например, между Windows-Linux машинами). Для передачи данных используйте
     * любой способ (через сокет, через файл, через строку).
     */

    static void task24(String filePath) throws IOException {
        System.out.println(LINE);
        System.out.println("24) Encode/Decode image to/from Base64 with charset\n");

        Thread clientThread = new PictureCharsetClientThread("Client");
        clientThread.start();

        //Server side begin

        try(ServerSocket server = new ServerSocket(1503)) {
            new PictureCharsetServerThread("Client_" + LocalTime.now(), server.accept(), filePath).run();
        }

        //Server side end

        try {
            clientThread.join();
        }
        catch (InterruptedException e) {
            System.out.println(clientThread.getName() + " finished");
        }
    }

}
