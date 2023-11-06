package org.iesvdm.kata1;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Person
{
    private final String firstName;
    private final String lastName;
    private final List<Pet> pets = new ArrayList<>();

    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getFullName()
    {
        return this.firstName + ' ' + this.lastName;
    }

    public boolean named(String name)
    {
        return name.equals(this.getFullName());
    }

    public boolean hasPet(PetType petType)
    {
        return this.pets.stream().anyMatch(p -> p.getType() == petType);
    }

    public boolean hasPet(String petEmoji)
    {
        return this.hasPet(PetType.fromEmoji(petEmoji));
    }

    public List<Pet> getPets()
    {
        return this.pets;
    }

    public Map<PetType, Long> getPetTypes()
    {
        return this.pets.stream().collect(groupingBy(Pet::getType, counting()));
    }

    public Map<String, Long> getPetEmojis()
    {
        final Map<String, Long> mapPetEmojis = new HashMap<>();
        this.getPetTypes().entrySet().stream().forEach( e -> mapPetEmojis.put(e.getKey().toString(), e.getValue()));
        return mapPetEmojis;

    }

    public Person addPet(PetType petType, String name, int age)
    {
        this.pets.add(new Pet(petType, name, age));
        return this;
    }

    public boolean isPetPerson()
    {
        return !this.pets.isEmpty();
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pets=" + pets +
                '}';
    }
}
