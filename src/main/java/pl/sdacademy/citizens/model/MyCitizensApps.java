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

    //nazwisko i id + imię

    public Map<String,Map<Long,String>> calculate_3(List<Person> people) {
        Map<String,Map<Long,String>> map = new LinkedHashMap<>();
        for (Person person:people){
            if(!(map.containsKey(person.getLastName())))
                map.put(person.getLastName(),count_3(people,person.getLastName()));
        }
        return map;
    }

    private Map<Long,String> count_3(List<Person> people, String currentlastName) {
        Map<Long,String> map = new LinkedHashMap<>();
        for (Person person:people){
            if (person.getLastName().equals(currentlastName))
                map.put(person.getId(),person.getName());
        }
        return map;
    }


    //zgrupowanie po imieniu

    public Map<String,List<Person>> calculate_4(List<Person> people) {
        Map<String,List<Person>> map = new LinkedHashMap<>();
        for (Person person:people){
            if (!(map.containsKey(person.getName())))
                map.put(person.getName(),count_4(people,person.getName()));
        }
        return map;
    }

    private List<Person> count_4(List<Person> people, String currentName) {
        List<Person> list = new LinkedList<>();
        for (Person person:people){
            if (person.getName().equals(currentName))
                list.add(person);
        }
        return list;
    }


    public List<Person> calculate_5(List<Person> people) {
        List<Person> list = new LinkedList<>();
        for (Person person:people){
            if ((person.getAge()>35)&&(person.getAge()<55)) list.add(person);
        }
        return list;
    }

    public List<Person> calculate_6(List<Person> people) {
        List<Person> list = new ArrayList<>();
        for (Person person:people){
            if ((person.getAge()>=60)&&(person.getSex().equals("F"))) list.add(person);
            if ((person.getAge()>=65)&&(person.getSex().equals("M"))) list.add(person);
        }
        return list;
    }
}
