package pl.sdacademy.citizens.model;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AnimalReader {
    public List<Animal> readFromFile(File fileName) throws ParseException {
        CsvFile csvLines = CsvFile.fromFile(fileName);
        List<Animal> animals = new ArrayList<>();
        for (CsvLine csvLine : csvLines) {
            animals.add(new Animal(csvLine));
        }
        return animals;
    }
}
//