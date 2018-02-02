package pl.sdacademy.citizens.model;


import java.util.*;

public class MyCitizensApps {

    //nazwiska i ilość wystąpień

    public Map<String,Integer> calculate(List<Person> people) {
        Map<String,Integer> map = new LinkedHashMap<>();
        for ( Person person:people ){
            if (!(map.containsKey(person.getLastName())))
                map.put(person.getLastName(),count(people,person.getLastName()));

        }
    return map;
    }

    private Integer count(List<Person> people, String currentlastName) {
        Integer counter=0;
        for (Person person:people){
            if (person.getLastName().equals(currentlastName))
                counter++;
        }
        return counter;
    }

    //nazwiska i lista imion

    public Map<String,List<String>> calculate_2(List<Person> people) {
        Map<String,List<String>> map = new LinkedHashMap<>();
        for (Person person:people) {
            if (!(map.containsKey(person.getLastName())))
                map.put(person.getLastName(),count_2(people,person.getLastName()));
        }
        return  map;
    }

    private List<String> count_2(List<Person> people, String currentlastName) {
        List<String> list = new LinkedList<>();
        for (Person person:people){
            if (person.getLastName().equals(currentlastName))
                list.add(person.getName());
        }
        return list;
    }

    //nazwisko

}
