package pl.sdacademy.citizens.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private Long id;
    private String name;
    private String lastName;
    private String sex;
    private LocalDate birthDate;
    private List<Animal> animals = new ArrayList<>();
    private String invalidEntryReason;

    public Person(Long id, String name, String lastName, String sex, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public Person(Long id, String name, String lastName, String sex, LocalDate birthDate, List<Animal> animals) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.animals = animals;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String lastName;
        private String sex;
        private LocalDate birthDate;
        private List<Animal> animals;

        public Builder(Long id, String name, String lastName, String sex, LocalDate birthDate) {
            this.id = id;
            this.name = name;
            this.lastName = lastName;
            this.sex = sex;
            this.birthDate = birthDate;
        }

        public Builder animals(List<Animal> animals) {
            this.animals = animals;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    private Person(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.sex = builder.sex;
        this.birthDate = builder.birthDate;
        this.animals = builder.animals;
    }

    public Person(CsvLine line) {
        id = Long.parseLong(line.getElementAt(0));
        name = line.getElementAt(1);
        lastName = line.getElementAt(2);
        sex = line.getElementAt(3);
        birthDate = LocalDate.parse(line.getElementAt(4));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public String getInvalidEntryReason() {
        return invalidEntryReason;
    }

    public void setInvalidEntryReason(String invalidEntryReason) {
        this.invalidEntryReason = invalidEntryReason;
    }

    public int getNumberOfAnimals() {
        return animals.size();
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public int getApproximateAge() {
        int age = getAge();
        return (int) Math.round(age / 5.0) * 5;
    }

    public boolean qualifiesToRetire() {
        return getAge() > 65 && sex.equals("M") || getAge() > 60 && sex.equals("F");
    }

    public boolean validateData() {
        invalidEntryReason = null;
        if (name.length() < 2) {
            invalidEntryReason = "Too short name";
            return false;
        }
        if (lastName.length() < 2) {
            invalidEntryReason = "Too short last name";
            return false;
        }
        if (!(sex.equals("M") || sex.equals("F"))) {
            invalidEntryReason = "Wrong sex";
            return false;
        }
        if (birthDate.isAfter(LocalDate.now())) {
            invalidEntryReason = "Hasn't been born yet";
            return false;
        }
        if (getAge() < 18) {
            invalidEntryReason = "Underage";
            return false;
        }
        return true;
    }

    public String getHiddenLastName() {
        char[] lastNameChars = lastName.toCharArray();
        for (int i = 1; i < lastNameChars.length - 1; i++) {
            lastNameChars[i] = '*';
        }
        return String.valueOf(lastNameChars);
    }

    public Integer[] noOfDogsCatsParrotsFish() {
        Integer[] array = {0, 0, 0, 0};
        if (getAnimals().isEmpty()) {
            return array;
        }
        List<Animal> personAnimals = getAnimals();
        for (Animal animal : personAnimals) {
            if (animal.getSpecies().equals("DOG")) {
                array[0] = array[0] + 1;
            }
            if (animal.getSpecies().equals("CAT")) {
                array[1] = array[1] + 1;
            }
            if (animal.getSpecies().equals("PARROT")) {
                array[2] = array[2] + 1;
            }
            if (animal.getSpecies().equals("FISH")) {
                array[3] = array[3] + 1;
            }
        }
        return array;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public String toString() {
        return "Person {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                ", animals=" + animals +
                "\n";
    }
}
