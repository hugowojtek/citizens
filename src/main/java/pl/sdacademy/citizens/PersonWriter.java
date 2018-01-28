package pl.sdacademy.citizens;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import pl.sdacademy.citizens.model.CsvFile;
import pl.sdacademy.citizens.model.Person;

import java.io.*;
import java.util.List;

public class PersonWriter {
    private static final String CSV_SEPARATOR = ",";

    public void writeModifiedEntries(File inputLocation, List<Person> people) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputLocation), "UTF-8"));
            bw.write(CsvFile.modifiedEntryToCSVString(people));
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void thirdMethod(File inputLocation, List<Person> people) {
        long start = System.currentTimeMillis();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputLocation), "UTF-8"));
            bw.write(CsvFile.toCSVString(people));
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        long stop = System.currentTimeMillis();
        System.out.println("Saving took: " + (stop - start) + " ms.");
    }

    public void secondMethod(File inputLocation, List<Person> people) {
        long start = System.currentTimeMillis();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputLocation), "UTF-8"));
            for (Person person : people) {
                StringBuilder oneLine = new StringBuilder();
                oneLine.append(person.getId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(person.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(person.getLastName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(person.getSex());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(person.getBirthDate());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long stop = System.currentTimeMillis();
        System.out.println("Saving took: " + (stop - start) + " ms.");
    }

    public void firstMethod(File inputLocation, List<Person> people) {
        long start = System.currentTimeMillis();
        FileWriter writer = null;
        try {
            writer = new FileWriter(inputLocation);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(people);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        long stop = System.currentTimeMillis();
        System.out.println("Saving took: " + (stop - start) + " ms.");
    }
}
