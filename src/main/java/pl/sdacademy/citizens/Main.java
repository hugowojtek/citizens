package pl.sdacademy.citizens;

import pl.sdacademy.citizens.model.Person;

import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {


        CitizensApplication app = new CitizensApplication();
        List<Person> people = app.process();

        /*      countLastName3(people,"KOWALCZYK");
        mapLastName2(people);
        System.out.println();
        app.writeModifiedEntries(people);
        app.write3(people);
     */
        LinkedHashMap<String,List<Person>> result = mapName(people);
        ShowMap(result);
        System.out.println();
    }


    public static LinkedHashMap<String, List<Person>> mapName(List<Person> people){
        LinkedHashMap<String,List<Person>> stringListHash = new LinkedHashMap<>();
        for (Person person:people){
            if (!stringListHash.containsKey(person.getName())){
                stringListHash.put(person.getName(), listName(people,person.getName()));
            }
        }
        return stringListHash;
    }

    public static List<Person> listName(List<Person> people,String name){
        List<Person> list = new LinkedList<>();
        for (Person person:people ){
            if (person.getName().equals(name)) list.add(person);
        }
        return list;
    }

    public static void ShowMap(LinkedHashMap<String,List<Person>> result){
        for (Map.Entry<String,List<Person>>entry:result.entrySet()){
            //System.out.println(entry.getKey()+"--"+entry.getValue());
            System.out.println(entry);
        }
    }










    public static void mapLastName2(List<Person> people){
        long start = System.currentTimeMillis();
        HashMap<String,Long> stringLongHashMap = new HashMap<>();
        for (Person person:people){
            if (!stringLongHashMap.containsKey(person.getLastName())){
                stringLongHashMap.put(person.getLastName(),countLastName2(people,person.getLastName()));
            }
        }
        long stop = System.currentTimeMillis();
        for (String s :stringLongHashMap.keySet()){
            System.out.println(s+stringLongHashMap.get(s));
        }

    }

    public static void countLastName3(List<Person> people, String lastName) {
        long count = people.stream().filter(person -> person.getLastName().equals(lastName)).count();
       /* long count = 0L;
        for (Person person : people) {
            if (person.getLastName().equals(lastName)) {
                count++;
            }
        }
    }
*/

        System.out.println(count+" osób o nazwisku "+lastName);
    }

    public static long countLastName2(List<Person> people, String lastName) {
        long count = people.stream().filter(person -> person.getLastName().equals(lastName)).count();
       /* long count = 0L;
        for (Person person : people) {
            if (person.getLastName().equals(lastName)) {
                count++;
            }
        }
    }
*/
        return count;
        //System.out.println(count+" osób o nazwisku "+lastName);
    }
}
