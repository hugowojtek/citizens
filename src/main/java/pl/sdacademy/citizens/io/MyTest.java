package pl.sdacademy.citizens.io;

import pl.sdacademy.citizens.exceptions.MyIOException;

import java.io.*;

public class MyTest {
    public static void main(String[] args) throws IOException{
        new IO_bytes().realization("src/sample.txt","src/result.txt");

    }
}
