package pl.sdacademy.citizens.io;

import pl.sdacademy.citizens.exceptions.MyIOException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyTest {
    public class MyClass extends File{
        public MyClass(String s) {
            super(s);
        }
    }
    public static void main(String[] args) throws IOException {
        new IO_bytes().realization("src/inBytes.txt", "src/outBytes.txt");
        new IO_character().realization("src/inCharacters.txt", "src/outCharacters.txt");
        new IO_bufferedStreams().realization("src/inBuffered.txt", "src/outBuffered.txt");
        File file = new File("src/outBytesy.txt");
        boolean b = file.exists();


        String str = file.getAbsolutePath();
        Path path = Paths.get("src/inBytes.txt");
        if (Files.exists(path)) System.out.println("true");
        System.out.println();
    }
}
