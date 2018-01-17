package pl.sdacademy.citizens.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Contains information about whole CSV file
 */
public class CsvFile implements Iterable<CsvLine> {
    private final List<CsvLine> lines;

    public CsvFile() {
        lines = new ArrayList<>();
    }

    /**
     * Appends this file with new CSV line
     *
     * @param line to be added
     */
    public void addLine(CsvLine line) {
        lines.add(line);
    }

    /**
     * Returns line at specified position
     *
     * @param lineNumber 0-based index
     * @return Found @{@link CsvLine} or null if not found
     */
    public CsvLine getLineAt(int lineNumber) {
        if (lineNumber < lines.size()) {
            return lines.get(lineNumber);
        }
        return null;
    }

    public List<CsvLine> getLines() {
        return new ArrayList<>(lines);
    }

    /**
     * Attempts to read provided file in order to convert it to CSV File representation
     *
     * @param inputLocation file that contains data.
     * @return Converted file, never null. File might actually does not contain any data.
     */
    public static CsvFile fromFile(File inputLocation) {
        CsvFile convertedFile = new CsvFile();
        try (FileReader inputStream = new FileReader(inputLocation)) {
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                convertedFile.addLine(CsvLine.fromTextLine(readLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedFile;
    }

    // PART OF METHOD 3 OF SAVING TO CSV
    public static String toCSVString(List<Person> people) {
        StringBuilder sb = new StringBuilder();
        for (Person person : people) {
            sb.append(CsvLine.toTextLine(person));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String modifiedEntryToCSVString(List<Person> people) {
        StringBuilder sb = new StringBuilder();
        for (Person person : people) {
            sb.append(CsvLine.modifiedEntryToTextLine(person));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public Iterator<CsvLine> iterator() {
        return lines.iterator();
    }
}
