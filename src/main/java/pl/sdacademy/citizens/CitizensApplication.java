package pl.sdacademy.citizens;

import pl.sdacademy.citizens.model.Animal;
import pl.sdacademy.citizens.model.Person;

import java.io.File;
import java.text.ParseException;
import java.util.*;

public class CitizensApplication {

    private PersonReader personReader;
    private PersonWriter personWriter;
    private AnimalReader animalReader;


    public CitizensApplication() {
        this.personReader = new PersonReader();
        this.personWriter = new PersonWriter();
        this.animalReader = new AnimalReader();
    }



    public List<Animal> processAnimals() throws ParseException{
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("animal_2.csv").getFile());
        List<Animal> animals = animalReader.readFromFile(file);
        /*    for (Animal animal:animals){
                System.out.println(animal);
            }*/
        return animals;
    }
    public List<Person> process() throws ParseException {
        File personFile = new File(getClass().getClassLoader().getResource("person_2.csv").getFile());
        List<Person> people = personReader.readFromFile(personFile);
        System.out.println("Starting process of adding animals to people");
        int i = 0;
        System.out.println("Done");
        return people;
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

    public void groupByName(List<Person> people) {
        Map<String, List<Person>> peopleMap = new TreeMap<>();
        for (Person person : people) {
            if (!peopleMap.containsKey(person.getName())) {
                peopleMap.put(person.getName(), getListOfPersonWithName(people, person.getName()));
            }
        }
/*
        for (String s : peopleMap.keySet()) {
            System.out.print(s);

            List<Person> people1 = peopleMap.get(s);
            for (Person person : people1) {
                System.out.println(" - " + person.getLastName() + " " + person.getBirthDate());
            }

        }
*/
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


    public void pairPersonAnimal(List<Person> people, List<Animal> animals) {
        for (Person person:people){
            for (Animal animal:animals){
                if (person.getId().equals(animal.getId())) person.addAnimal(animal);
            }
        }
    }

    public void filterPerson(List<Person> people) {
        List<Person> personList = new ArrayList<>();
        personList  = new PeopleUtilities(people).filterByAgeFrom35To55();


        }

    public void checkQuantityOfAnimals(List<Person> people) {
        int male=0;
        int female=0;
        for (Person person:people) {
            if ((person.getSex().equals("M")&&person.getAnimalList()!=null)) male++;
            if ((person.getSex().equals("F")&&person.getAnimalList()!=null)) female++;
        }
        System.out.println("Faceci mają w sumie:" + male+" zwierząt");
        System.out.println("Kobity mają w sumie:" + female+" zwierząt");
    }
}


