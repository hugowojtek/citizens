package pl.sdacademy.citizens;

import pl.sdacademy.citizens.model.Animal;
import pl.sdacademy.citizens.model.Person;

import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        CitizensApplication app = new CitizensApplication();
        List<Person> people = app.process();
        List<Animal> animals = app.processAnimals();

        Map<String,Integer> map1 = new MyCitizensApps().calculate(people);

        Map<String,List<String>> map2 = new MyCitizensApps().calculate_2(people);

        Map<String,Map<Long,String>> map3 = new MyCitizensApps().calculate_3(people);

        Map<String,List<Person>> map4 = new MyCitizensApps().calculate_4(people);

        List<Person> list1 = new MyCitizensApps().calculate_5(people);

        List<Person> list2 = new MyCitizensApps().calculate_6(people);

        List<Person> list3 = new MyCitizensApps().calculate_7(people);

        List<Person> list4 = new MyCitizensApps().calculate_8(people, animals);

        Map<String,Integer> map5 = new MyCitizensApps().calculate_9(animals);

        Map<String,List<Person>> map6 = new MyCitizensApps().calculate_10(people,animals);


        System.out.println();
//        app.mapLastName(people);
//        System.out.println("==============================");
//        app.mapLastNameLambda(people);
//        app.writeModifiedEntries(people);
//        app.write1(people);
     }
}
//