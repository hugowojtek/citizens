package pl.sdacademy.citizens;

import pl.sdacademy.citizens.model.Animal;
import pl.sdacademy.citizens.model.Person;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * W klasie CitizensApplication napisz metodę,
     * która będzie zliczać, ile jest osób z danym nazwiskiem.
     * @param people
     */
    public long countLastName(List<Person> people, String lastName){
        long count = people.stream().filter(person -> person.getLastName().equals(lastName)).count();
        /*
        long count = 0L;
        for (Person person : people) {
            if (person.getLastName().equals(lastName)) {
                count++;
            }
        }
         */
        return count;
//        System.out.println(count + " osób o nazwisku "+ lastName);
    }

    /**
     * Zliczanie osób w sposób iteracyjny
     * nazwisko - > ilość wystąpień
     * @param people
     */
    public void mapLastName(List<Person> people){
        long start = System.currentTimeMillis();
        HashMap<String, Long> stringLongHashMap = new HashMap<>();
        for (Person person : people) {
            if (!stringLongHashMap.containsKey(person.getLastName())) {
                stringLongHashMap.put(person.getLastName(), countLastName(people, person.getLastName()));
            }
        }
        long stop = System.currentTimeMillis();
        for (String s : stringLongHashMap.keySet()) {
            System.out.println(s +" -> "+ stringLongHashMap.get(s));
        }
        System.out.println("Policzone w "+ (stop - start));
    }

    public void mapLastNameLambda(List<Person> people){
        PeopleUtilities peopleUtilities = new PeopleUtilities(people);
        long start = System.currentTimeMillis();
        Map<String, Long> lastNameSummary = peopleUtilities.createLastNameSummary();
        long stop = System.currentTimeMillis() - start;

        for (String s : lastNameSummary.keySet()) {
            System.out.println(s+" -> "+ lastNameSummary.get(s));
        }

        System.out.println("Policzone w "+ stop);

    }

    public void groupByName(List<Person> people){
        HashMap<String, List<Person>> pipulHashMap = new HashMap<>();
        for (Person person : people) {
            if (!pipulHashMap.containsKey(person.getName())) {
                pipulHashMap.put(person.getName(), getListOfPersonWithName(people, person.getName()));
            }
        }

        for (String s : pipulHashMap.keySet()) {
            List<Person> people1 = pipulHashMap.get(s);
            System.out.print(s);
            for (Person person : people1) {
                System.out.println(" - "+person.getLastName()+" "+ person.getBirthDate());
            }
        }

        //na wyjsciu wyswietla listę osób dla imienia
    }

    private List<Person> getListOfPersonWithName(List<Person> people, String name) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : people) {
            if (person.getName().equals(name)) {
                result.add(person);
            }
        }
        return result;
    }
}

