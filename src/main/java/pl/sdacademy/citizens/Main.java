package pl.sdacademy.citizens;

import pl.sdacademy.citizens.model.Animal;
import pl.sdacademy.citizens.model.MyCitizensApps;
import pl.sdacademy.citizens.model.Person;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws ParseException {
        CitizensApplication app = new CitizensApplication();
        List<Person> people = app.process();
        List<Animal> animals = app.processAnimals();

        Map<String,Integer> map1 = new MyCitizensApps().calculate(people);

        Map<String,List<String>> map2 = new MyCitizensApps().calculate_2(people);

        System.out.println();
//        app.mapLastName(people);
//        System.out.println("==============================");
//        app.mapLastNameLambda(people);
//        app.writeModifiedEntries(people);
//        app.write1(people);
     }
}
//