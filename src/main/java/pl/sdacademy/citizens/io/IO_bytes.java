package pl.sdacademy.citizens.io;

import pl.sdacademy.citizens.exceptions.MyIOException;

import java.io.*;

public class IO_bytes {

    public void realization(String s, String s1) throws IOException{
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        int c;
        int i = 0;
        int[] tab = new int[255];
        try {
            inputStream = new FileInputStream(s);
            outputStream = new FileOutputStream(s1);

            while ((c = inputStream.read()) != -1) {
                tab[i++] = c;
                outputStream.write(c);

            }
        } finally {

            if (inputStream!= null)
                inputStream.close();

            if (outputStream != null)
                outputStream.close();

        }

        System.out.println();
    }
}
