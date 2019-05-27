package Utils.NIO;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class ShowDirectory extends SimpleFileVisitor<Path> {
    private String extension;

    public ShowDirectory() {}

    public ShowDirectory(String extension) {
        if(extension.length() > 0 && !extension.substring(0,1).equals(".")) extension = "." + extension;
        this.extension = extension;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(this.extension == null) System.out.println(file);
        else if(file.getFileName().toString().endsWith(extension)) System.out.println(file);

        return FileVisitResult.CONTINUE;
    }
}
