package pl.sdacademy.citizens.io;

import java.io.*;

public class IO_character {
    public void realization(String s, String s1) throws IOException{
        Reader fileReader = null;
        Writer fileWriter = null;
        int c;
        char[] cbuf = new char[20];

        try {
            fileReader = new FileReader(s);
            fileWriter = new FileWriter(s1);

/*
            while ((c = fileReader.read()) != -1) {
                fileWriter.write(c);
            }
*/
/*
            while((c=fileReader.read(cbuf))!=-1){
                fileWriter.write(c);
            }
*/
            while ((c=fileReader.read(cbuf,3,3))!=-1){
                fileWriter.write(c);
            }
        } finally {
            if (fileReader != null) fileReader.close();
            if (fileWriter != null) fileWriter.close();
        }
    }

}
