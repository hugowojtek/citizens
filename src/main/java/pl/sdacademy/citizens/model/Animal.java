package pl.sdacademy.citizens.model;


public class Animal {
    private Long id;
    private String name;
    private String species;

    public Animal(Long id, String name, String species) {
        this.id = id;
        this.name = name;
        this.species = species;
    }

    public Animal(CsvLine line) {
        id = Long.parseLong(line.getElementAt(0));
        name = line.getElementAt(1);
        species = line.getElementAt(2);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                '}';
    }
}
