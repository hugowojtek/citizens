package pl.sdacademy.citizens;

import pl.sdacademy.citizens.exceptions.EmptyListException;
import pl.sdacademy.citizens.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PeopleUtilities {
    private List<Person> people;

    public PeopleUtilities(List<Person> people) {
        this.people = people;
    }

    public long countByName(String name) {
        emptyListValidation();
        return people.stream().filter(person -> person.getName().equals(name)).count();
    }

    public Map<String, List<Person>> groupByName() {
        emptyListValidation();
        return people.stream().collect(Collectors.groupingBy(Person::getName, Collectors.toList()));
    }

    public List<Person> filterByAgeFrom35To55() {
        emptyListValidation();
        return people.stream().filter(p -> (p.getAge() > 35 && p.getAge() < 55)).collect(Collectors.toList());
    }

    public long countPeopleQualifiedToRetire() {
        emptyListValidation();
        return people.stream().filter(Person::qualifiesToRetire).count();
    }

    public Map<String, Long> createNameSummary() {
        emptyListValidation();
        return people.stream()
                .map(Person::getName)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    public Map<String, Long> createLastNameSummary() {
        emptyListValidation();
        return people.stream()
                .map(Person::getLastName)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    public List<Person> validatePersonEntries() {
        emptyListValidation();
        return people.stream().filter(Person::validateData).collect(Collectors.toList());
    }

    public List<Person> extractInvalidPersonEntries() {
        emptyListValidation();

        return people.stream().filter(person -> !person.validateData()).collect(Collectors.toList());
    }

    public List<Person> peopleWithAtLeastTwoAnimals() {
        emptyListValidation();
        return people.stream()
                .filter(person -> person.getNumberOfAnimals() >= 2)
                .collect(Collectors.toList());
    }

    private void emptyListValidation() {
        if (people.isEmpty()) {
            throw new EmptyListException("The list is empty!");
        }
    }
}
