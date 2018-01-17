package pl.sdacademy.citizens;

public class Animal {
    private long id;
    private String name;
    private String species;

    public Animal(long id, String name, String species) {
        this.id = id;
        this.name = name;
        this.species = species;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }
}
