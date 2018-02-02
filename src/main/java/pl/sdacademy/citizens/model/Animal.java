package pl.sdacademy.citizens.model;


public class Animal {
    private Long id;
    private String name;
    private String species;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Animal(Long id, String name, String species) {
        this.id = id;
        this.name = name;
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


    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name= " + name +
                ", gatunek= " + species + '\'' +
                '}';
    }
}
