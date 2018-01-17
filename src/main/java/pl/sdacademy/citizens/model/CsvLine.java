package pl.sdacademy.citizens.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

/**
 * Contains information about single CSV file line.
 */
public class CsvLine implements Iterable<String> {
    private final List<String> elements;
    private static final String CSV_SEPARATOR = ",";

    public CsvLine() {
        this.elements = new ArrayList<>();
    }

    /**
     * Appends current line with new value
     *
     * @param value to be added
     */
    public void addElement(String value) {
        elements.add(value);
    }

    /**
     * Retrieves value at specific index of line.
     *
     * @param position 0-based index of value
     * @return @{@link String} value, or null in no element found
     */
    public String getElementAt(int position) {
        if (position < elements.size()) {
            return elements.get(position);
        }
        return null;
    }

    /**
     * Returns copy of all elements in this line.
     *
     * @return @{@link List} of @{@link String} elements, never null. Might return 0-length collection.
     */
    public List<String> getElements() {
        return new ArrayList<>(elements);
    }

    /**
     * Attempts to split provided line and create CsvLine from its values.
     *
     * @param textLine comma-separated values
     * @return
     */
    public static CsvLine fromTextLine(String textLine) {
        String[] splittedLine = textLine.split(",");
        CsvLine csvLine = new CsvLine();
        for (String lineElement : splittedLine) {
            csvLine.addElement(lineElement);
        }
        return csvLine;
    }

    // PART OF METHOD 3 OF SAVING TO CSV
    public static String toTextLine(Person person) {
        StringJoiner sj = new StringJoiner(", ");
        sj.add(person.getId().toString())
                .add(person.getName())
                .add(person.getLastName())
                .add(person.getSex())
                .add(String.valueOf(person.getBirthDate()))
                .add(person.getAnimals().toString());
        if (!(person.getInvalidEntryReason() == null)) {
            sj.add(person.getInvalidEntryReason());
        }
        return sj.toString();
    }

    public static String modifiedEntryToTextLine(Person person) {
        StringJoiner sj = new StringJoiner(", ");
        Integer[] array = person.noOfDogsCatsParrotsFish();
        sj.add(person.getId().toString())
                .add(person.getName())
                .add(person.getHiddenLastName())
                .add(String.valueOf(person.getApproximateAge()))
                .add(String.valueOf(array[0]))
                .add(String.valueOf(array[1]))
                .add(String.valueOf(array[2]))
                .add(String.valueOf(array[3]));
        return sj.toString();
    }

    @Override
    public Iterator<String> iterator() {
        return elements.iterator();
    }
}
