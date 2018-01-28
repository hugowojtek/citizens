package pl.sdacademy.citizens;

import pl.sdacademy.citizens.model.Person;

import java.text.ParseException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {
        CitizensApplication app = new CitizensApplication();
        List<Person> people = app.process();
        app.groupByName(people);
//        app.mapLastName(people);
//        System.out.println("==============================");
//        app.mapLastNameLambda(people);
//        app.writeModifiedEntries(people);
//        app.write1(people);
     }
}
