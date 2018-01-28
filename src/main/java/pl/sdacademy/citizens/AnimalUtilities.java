package pl.sdacademy.citizens;

import pl.sdacademy.citizens.exceptions.EmptyListException;
import pl.sdacademy.citizens.model.Animal;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnimalUtilities {
    private List<Animal> animals;

    public AnimalUtilities(List<Animal> animals) {
        this.animals = animals;
    }

    /**
     * Creates a Map where Key is animal species and Value is a number of
     * occurrences of that species in a given list
     *
     * @return Map as written above
     */

    private void emptyListValidation() {
        if (animals.isEmpty()) {
            throw new EmptyListException("The list is empty!");
        }
    }
}
