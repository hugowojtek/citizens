package pl.sdacademy.citizens;

import pl.sdacademy.citizens.model.Animal;
import pl.sdacademy.citizens.model.Person;

import java.io.File;
import java.text.ParseException;
import java.util.List;

public class CitizensApplication {

    private PersonReader personReader;
    private AnimalReader animalReader;
    private PersonWriter personWriter;


    public CitizensApplication() {
        this.personReader = new PersonReader();
        this.animalReader = new AnimalReader();
        this.personWriter = new PersonWriter();
    }

    public List<Person> process() throws ParseException {
        File personFile = new File(getClass().getClassLoader().getResource("person.csv").getFile());
        List<Person> people = personReader.readFromFile(personFile);
        List<Animal> animals = processAnimals();
        System.out.println("Starting process of adding animals to people");
        int i = 0;
        for (Animal animal : animals) {
            while (i < people.size()) {
                if (animal.getId().equals(people.get(i).getId())) {
                    people.get(i).addAnimal(animal);
                    break;
                } else {
                    i++;
                }
            }
        }
        System.out.println("Done");
        return people;
    }

    private List<Animal> processAnimals() {
        File animalFile = new File(getClass().getClassLoader().getResource("animal.csv").getFile());
        return animalReader.readFromFile(animalFile);
    }

    public void writeModifiedEntries(List<Person> people) {
        File destination = new File("src\\main\\resources\\modified_entries.csv");
        personWriter.writeModifiedEntries(destination, people);
    }

    public void write3(List<Person> people) {
        File destination = null;
        if (people.get(0).getInvalidEntryReason() != null) {
            destination = new File("src\\main\\resources\\invalid_entries.csv");
        } else {
            destination = new File("src\\main\\resources\\method3.csv");
        }
        personWriter.thirdMethod(destination, people);
    }

    public void write2(List<Person> people) {
        File destination = new File("src\\main\\resources\\method2.csv");
        personWriter.secondMethod(destination, people);
    }

    public void write1(List<Person> people) {
        File destination = new File("src\\main\\resources\\method1.csv");
        personWriter.firstMethod(destination, people);
    }
}

