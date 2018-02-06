package pl.sdacademy.citizens;


import org.apache.commons.collections.map.LinkedMap;
import pl.sdacademy.citizens.model.Animal;
import pl.sdacademy.citizens.model.Person;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MyCitizensApps {

    //nazwiska i ilość wystąpień

    public Map<String, Integer> calculate(List<Person> people) {
        long start = System.currentTimeMillis();
        Map<String, Integer> map = new HashMap<>();
        for (Person person : people) {
            if (!(map.containsKey(person.getLastName())))
                map.put(person.getLastName(), count(people, person.getLastName()));

        }
        long stop = System.currentTimeMillis();
        System.out.println("Policzone metoda_1 w "+ (stop - start));
        return map;
    }

    private Integer count(List<Person> people, String currentlastName) {
        Integer counter = 0;
        for (Person person : people) {
            if (person.getLastName().equals(currentlastName))
                counter++;
        }
        return counter;
    }

    //nazwiska i ilość wystąpień inna metoda
    public Map<String,Integer> calculate_1(List<Person> people) {
        long start = System.currentTimeMillis();
        Map<String,Integer> map = new HashMap<>();
        for (Person person:people){
            int val = map.getOrDefault(person.getLastName(),0);
            val++;
            map.put(person.getLastName(),val);
        }
        long stop = System.currentTimeMillis();
        System.out.println("Policzone metoda_2 w "+ (stop - start));
        return map;
    }

    //nazwiska i ilość imion streamy
    public Map<String,Integer> calculate_1s(List<Person> people) {
        //Map<String,Integer> collect = people.stream().collect(Collectors.groupingBy(Person::getLastName,Collectors.toList()))
        return null;
    }

    //nazwiska i lista imion
    public Map<String, List<String>> calculate_2(List<Person> people) {
        Map<String, List<String>> map = new LinkedHashMap<>();
        for (Person person : people) {
            if (!(map.containsKey(person.getLastName())))
                map.put(person.getLastName(), count_2(people, person.getLastName()));
        }
        return map;
    }




    private List<String> count_2(List<Person> people, String currentlastName) {
        List<String> list = new LinkedList<>();
        for (Person person : people) {
            if (person.getLastName().equals(currentlastName))
                list.add(person.getName());
        }
        return list;
    }

    //nazwisko i id + imię

    public Map<String, Map<Long, String>> calculate_3(List<Person> people) {
        Map<String, Map<Long, String>> map = new LinkedHashMap<>();
        for (Person person : people) {
            if (!(map.containsKey(person.getLastName())))
                map.put(person.getLastName(), count_3(people, person.getLastName()));
        }
        return map;
    }

    private Map<Long, String> count_3(List<Person> people, String currentlastName) {
        Map<Long, String> map = new LinkedHashMap<>();
        for (Person person : people) {
            if (person.getLastName().equals(currentlastName))
                map.put(person.getId(), person.getName());
        }
        return map;
    }


    //zgrupowanie po imieniu

    public Map<String, List<Person>> calculate_4(List<Person> people) {
        Map<String, List<Person>> map = new LinkedHashMap<>();
        for (Person person : people) {
            if (!(map.containsKey(person.getName())))
                map.put(person.getName(), count_4(people, person.getName()));
        }
        return map;
    }

    private List<Person> count_4(List<Person> people, String currentName) {
        List<Person> list = new LinkedList<>();
        for (Person person : people) {
            if (person.getName().equals(currentName))
                list.add(person);
        }
        return list;
    }


    public List<Person> calculate_5(List<Person> people) {
        List<Person> list = new LinkedList<>();
        for (Person person : people) {
            if ((person.getAge() > 35) && (person.getAge() < 55)) list.add(person);
        }
        return list;
    }

    public List<Person> calculate_6(List<Person> people) {
        long start = System.currentTimeMillis();
        List<Person> list = new ArrayList<>();
        for (Person person : people) {
            if ((person.getAge() >= 60) && (person.getSex().equals("F"))) list.add(person);
            if ((person.getAge() >= 65) && (person.getSex().equals("M"))) list.add(person);
        }
        long stop = System.currentTimeMillis();
        System.out.println("foreach emerytowanych czas: "+(stop-start) );
        return list;
    }
    //name of person is neither empty nor shorter than 2 characters etc

    public List<Person> calculate_7(List<Person> people) {
        List<Person> list = new ArrayList<>();
        for (Person person : people) {
            if (person.validateData()) list.add(person);
        }
        return list;
    }

    //add list of animals to person

    public List<Person> calculate_8(List<Person> people, List<Animal> animals) {
        List<Person> list = new ArrayList<>();
        for (Person person : people) {
            for (Animal animal : animals) {
                if (person.getId().equals(animal.getId())) {
                    person.addAnimal(animal);

                }

            }
            list.add(person);
        }
        return list;
    }

    //count how many animals every species

    public Map<String, Integer> calculate_9(List<Animal> animals) {
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        for (Animal animal : animals) {
            if (!(map.containsKey(animal.getSpecies()))) {
                map.put(animal.getSpecies(), count_9(animals, animal.getSpecies()));
            }
        }
        return map;

    }


    private Integer count_9(List<Animal> animals, String species) {
        Integer counter = 0;
        for (Animal animal : animals) {
            if (animal.getSpecies().equals(species)) {
                counter++;
            }
        }
        return counter;
    }

    //count how many people have at least ANIMAL_LIMITS

    public List<Person> calculate_10(List<Person> people) {
        final int ANIMAL_LIMITS = 2;
        List<Person> list = new ArrayList<>();
        for (Person person : people) {
            if ((person.getAnimalList() != null) && (person.getAnimalList().size() >= ANIMAL_LIMITS)) list.add(person);
        }
        return list;
    }

    //filter of people

    public List<Person> calculate_11(List<Person> people) {
        long start = System.currentTimeMillis();
        List<Person> list = new ArrayList<>();
        for (Person person : people) {
            if ((person.getName()==null)||(!(person.getName().length()>=2))) continue;
            if ((person.getLastName()==null)||(!(person.getLastName().length()>=2))) continue;
            if (!(person.getSex().length()==1)) continue;
            if ((person.getBirthDate().isAfter(LocalDate.now()))) continue;
            if (!((LocalDate.now().getYear() - person.getBirthDate().getYear()) >= 18)) continue;
            if (((LocalDate.now().getYear() - person.getBirthDate().getYear()==18)&&
                    (!(LocalDate.now().getDayOfYear() > person.getBirthDate().getDayOfYear())))) continue;

            list.add(person);
        }
        long stop = System.currentTimeMillis();
        System.out.println("foreach filtrowanie: "+(stop-start));
        return list;
    }

    //rename of Last name asterix codes

    public List<Person> calculate_12(List<Person> people) {
        List<Person> list = new ArrayList<>();
        for (Person person : people) {

            if (person.getLastName().length() < 2) continue;
            char[] table = person.getLastName().toCharArray();
            char startLetter = table[0];
            char endLetter = table[table.length - 1];
            String strEndLetter = String.valueOf(endLetter).toLowerCase();
            String asterix = "*****";
            String result = startLetter + asterix + strEndLetter;
            person.setLastName(result);
            list.add(person);

        }
        return list;

    }

    //grouping of people

    public Map<Integer, List<Person>> calculate_13(List<Person> people) {
        Map<Integer, List<Person>> map = new LinkedHashMap<>();
        List<Person> list25 = new ArrayList<>();
        List<Person> list35 = new ArrayList<>();
        List<Person> list45 = new ArrayList<>();
        List<Person> listDown = new ArrayList<>();
        List<Person> listUp = new ArrayList<>();

        for (Person person : people) {

            if (((LocalDate.now().getYear() - person.getBirthDate().getYear()) <= 20)) {
                listDown.add(person);
                continue;
            }

            if (((LocalDate.now().getYear() - person.getBirthDate().getYear()) > 20) && ((LocalDate.now().getYear() - person.getBirthDate().getYear()) <= 30)) {
                list25.add(person);
                continue;
            }
            if (((LocalDate.now().getYear() - person.getBirthDate().getYear()) > 30) && ((LocalDate.now().getYear() - person.getBirthDate().getYear()) <= 40)) {
                list35.add(person);
                continue;
            }
            if (((LocalDate.now().getYear() - person.getBirthDate().getYear()) > 40) && ((LocalDate.now().getYear() - person.getBirthDate().getYear()) <= 50)) {
                list45.add(person);
                continue;
            }
            if ((LocalDate.now().getYear() - person.getBirthDate().getYear()) > 50) {
                listUp.add(person);
                continue;
            }

        }
        map.put(20, listDown);
        map.put(25, list25);
        map.put(35, list35);
        map.put(45, list45);
        map.put(50, listUp);

        return map;
    }

    //counting of animals from people(animalsList) object

    public Map<String, Integer> calculate_14(List<Person> people) {
        Map<String, Integer> map = new LinkedMap();
        Integer cats = 0;
        Integer parrots = 0;
        Integer fishes = 0;
        Integer dogs = 0;
        for (Person person : people) {
            if (person.getAnimalList() == null) continue;
            for (Animal animal : person.getAnimalList()) {

                if (animal.getSpecies().equals("CAT")) {
                    cats++;
                }
                if (animal.getSpecies().equals("PARROT")) {
                    parrots++;
                }
                if (animal.getSpecies().equals("FISH")) {
                    fishes++;
                }
                if (animal.getSpecies().equals("DOG")) {
                    dogs++;
                }
            }

        }
        map.put("CAT", cats);
        map.put("PARROT", parrots);
        map.put("FISH", fishes);
        map.put("DOG", dogs);

        return map;
    }

    public Integer calculate_15(List<Person> people,Long id_person,String animalSpecies) {
        Integer id=0;
           for (Person person:people){
               if (person.getId().equals(id_person)){
                   for (Animal animal:person.getAnimalList()){
                       if (animal.getSpecies().equals(animalSpecies)) id++;
                   }

               }
           }
        return id;
    }


    public Map<String,List<Person>> calculate_50(List<Person> people) {
        return null;
    }
}





