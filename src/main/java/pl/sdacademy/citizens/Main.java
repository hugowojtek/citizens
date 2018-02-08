package pl.sdacademy.citizens;

import pl.sdacademy.citizens.model.Animal;
import pl.sdacademy.citizens.model.Person;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws ParseException {
        CitizensApplication app = new CitizensApplication();
        List<Person> people = app.process();
        List<Animal> animals = app.processAnimals();



        List<Person> list50 = selectByName(people,"Marcin");

        List<Person> list51 = selectedByName2(people,"Marcin");

        Map<String,Integer> map0 = new MyCitizensApps().calculate(people);

        Map<String,Integer> map1 = new MyCitizensApps().calculate_1(people);

        Map<String,Long> map0_1 = countingByLastName(people);


        Map<String,List<String>> map2 = new MyCitizensApps().calculate_2(people);

        Map<String,Map<Long,String>> map3 = new MyCitizensApps().calculate_3(people);

        Map<String,List<Person>> map4 = new MyCitizensApps().calculate_4(people);

        Map<String, List<Person>> map4_01 = nameGrouping(people);

        Map<String,List<Person>> map4_02 = nameGroupingFiltered(people,"A");

        Map<String, List<Person>> map50 = new MyCitizensApps().calculate_50(people);

        Map<String, List<Person>> map50_1 = sexGrouping(people);

        List<Person> list60 = selectedByAges(people);

        List<Person> list70 = selectedRentiredPerson(people);

        long counter = CountRentiredPeopleForEach(people);

        long counter1 = CountRentiredPeopleLambda(people);

        List<Person> list1 = new MyCitizensApps().calculate_5(people);

        List<Person> list2_1 = new MyCitizensApps().calculate_6(people);

        List<Person> list2_2 = selectedRentiredPerson(people);

        List<Person> list3 = new MyCitizensApps().calculate_7(people);

        List<Person> list4 = new MyCitizensApps().calculate_8(people, animals);

        Map<String,Integer> map5_1 = new MyCitizensApps().calculate_90(animals);

        Map<String,Integer> map5_2 = new MyCitizensApps().calculate_91(animals);

        Map<String,Long> map5_3 = CoutingAnimalsBySpecies(animals);

        List<Person> list5_4 = new MyCitizensApps().calculate_10(list4);
        
        List<Person> list5_5 = CountingPersonHaveLimitAnimals(list4,2);

        List<Person> list6_1 = new MyCitizensApps().calculate_11(people);

        List<Person> list6_2 = filterLambda(people);

        List<Person> list7 = new MyCitizensApps().calculate_12(list4);

        Map<Integer,List<Person>> map6 = new MyCitizensApps().calculate_13(people);

        Map<String,Integer> map7 = new MyCitizensApps().calculate_14(list4);

        Long id_person=15L;
        String species="CAT";
        Integer cats = new MyCitizensApps().calculate_15(people,id_person,species);

        //Long animalsy = counterOfAnimalsAtPerson(people,id_person,species);

        System.out.println();
//        app.mapLastName(people);
//        System.out.println("==============================");
//        app.mapLastNameLambda(people);
//        app.writeModifiedEntries(people);
//        app.write1(people);
     }



    private static List<Person> CountingPersonHaveLimitAnimals(List<Person> people, final int i) {
        List<Person> list = people.stream().filter(person -> (person.getAnimalList()!=null)&&(person.getAnimalList().size()>=i)).collect(Collectors.toList());
        return list;
    }

    private static Map<String,Long> CoutingAnimalsBySpecies(List<Animal> animals) {
        long start = System.currentTimeMillis();
        Map<String,Long> map = animals.stream().map(Animal::getSpecies)
                .collect(Collectors.groupingBy(animal->animal,Collectors.counting()));
        long stop = System.currentTimeMillis();
        System.out.println("met3: "+(stop-start));
        return map;
    }


    private static List<Person> filterLambda(List<Person> people) {
        long start = System.currentTimeMillis();
        List<Person> list = people.stream().filter(person -> filterValidate(person)).collect(Collectors.toList());
        long stop = System.currentTimeMillis();
        System.out.println("lambda filtrowanie: "+(stop-start));
        return list;
    }

    private static boolean filterValidate(Person person) {
        if ((person.getName()==null)||((person.getName().length()<2))) return false;
        if ((person.getLastName()==null)||((person.getLastName().length()<2))) return false;
        if ((person.getSex().length()!=1)) return false;
        if ((person.getBirthDate().isAfter(LocalDate.now()))) return false;
        if (((LocalDate.now().getYear() - person.getBirthDate().getYear()) < 18)) return false;
        if (((LocalDate.now().getYear() - person.getBirthDate().getYear()==18)&&
            (!(LocalDate.now().getDayOfYear() > person.getBirthDate().getDayOfYear())))) return false;
        return true;
    }


    private static List<Person> selectedRentiredPerson(List<Person> people) {
        long start = System.currentTimeMillis();
        List<Person> list = people.stream()
                .filter(person -> person.getAge()>=60&&person.getSex().equals("F")||person.getAge()>=65&&person.getSex().equals("M"))
                .collect(Collectors.toList());
        long stop = System.currentTimeMillis();
        System.out.println("lambda emerytowanych czas: "+(stop-start) );
        return list;
    }

    private static long CountRentiredPeopleForEach(List<Person> people) {
        long start = System.currentTimeMillis();
        long counter = 0;
        for (Person person : people) {
            if ((person.getSex().equals("F") && (person.getAge() >= 60)) || (person.getSex().equals("M")) && (person.getAge() >= 65))
                counter++;
        }
        long stop = System.currentTimeMillis();
        System.out.println(" foreach long: "+(stop-start) );
        return counter;
    }
    private static long CountRentiredPeopleLambda(List<Person> people) {
        long start= System.currentTimeMillis();
        long counter = people.stream()
                .filter(person -> ((person.getSex().equals("F") && (person.getAge() >= 60))||((person.getSex().equals("M") && (person.getAge() >= 65)))))
                .count();
        long stop = System.currentTimeMillis();
        System.out.println(" lambda long: "+(stop-start) );
        return counter;

    }


    private static Map<String, List<Person>> nameGrouping(List<Person> people) {
        Map<String,List<Person>> map = people.stream().collect(Collectors.groupingBy(Person::getName));
        return map;
    }

    private static Map<String, List<Person>> nameGroupingFiltered(List<Person> people, String str) {
        Map<String,List<Person>> map = people.stream()
                .filter(person -> person.getName().startsWith(str))
                .collect(Collectors.groupingBy(Person::getName));
        return map;
    }



    private static Map<String, List<Person>> sexGrouping(List<Person> people) {
        Map<String,List<Person>> map = people.stream()
                .filter(person -> validateSex(person))
                .collect(Collectors.groupingBy(Person::getSex));
        return map;
    }




    private static boolean validateSex(Person person){
        if (person.getSex().equals("M")||(person.getSex().equals("F"))) return true;
        return false;
    }


    private static Map<String,Long> countingByLastName(List<Person> people) {
        Long start = System.currentTimeMillis();
        Map<String,Long> map = people.stream()
                .map(Person::getLastName).collect(Collectors.groupingBy(person -> person, Collectors.counting()));
        Long stop = System.currentTimeMillis();
        System.out.println("Policzone metoda_3 w "+ (stop - start));
        return map;
    }


    private static List<Person> selectedByName2(List<Person> people, String word) {
        long start = System.currentTimeMillis();
        List<Person> list = new ArrayList();
        for (Person person:people) {
            if (person.getName().equals(word)) list.add(person);
        }
        long stop = System.currentTimeMillis();
        System.out.println("metoda2"+"\t"+(stop-start));
        return list;
    }

    private static List<Person> selectByName(List<Person> people, String word) {
        long start = System.currentTimeMillis();
        List<Person> selectedPerson = people.stream()
                .filter(person -> person.getName().equals(word))
                .collect(Collectors.toList());
        long stop= System.currentTimeMillis();
        System.out.println("metoda1"+"\t"+(stop-start));
        return selectedPerson;
    }

    private static List<Person> selectedByAges(List<Person> people){
        List<Person> list = people.stream()
                .filter(person -> person.getAge()>35&&person.getAge()<55)
                .collect(Collectors.toList());
        return list;
    }
}
//