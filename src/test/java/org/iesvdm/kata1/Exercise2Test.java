package org.iesvdm.kata1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise2Test extends PetDomainForKata
{
    @Test
    @Tag("KATA")
    @DisplayName("doAnyPeopleHaveCats 🐱?")
    public void doAnyPeopleHaveCats()
    {
        //TODO
        // replace null with a Predicate lambda which checks for PetType.CAT
        Predicate<Person> predicate = p -> p.hasPet(PetType.CAT);

        //TODO
        // replace false by a check in a stream of people
        Assertions.assertTrue(people.stream().filter(predicate).findAny().isPresent());
    }

    @Test
    @Tag("KATA")
    public void doAllPeopleHavePets()
    {

        Predicate<Person> predicate = Person::isPetPerson;
        //TODO
        // replace with a method call send to people that checks if all people have pets
        boolean result = people.size() == people.stream().filter(predicate).toList().size();

        Assertions.assertFalse(result);
    }

    @Test
    @Tag("KATA")
    @DisplayName("howManyPeopleHaveCats 🐱?")
    public void howManyPeopleHaveCats()
    {
        //TODO
        // replace with a method call send to this.people that checks how many people have cats
        int count = people.stream().filter(person -> person.hasPet(PetType.CAT)).toList().size();

        Assertions.assertEquals(2, count);
    }

    @Test
    @Tag("KATA")
    public void findMarySmith()
    {
        //TODO
        // replace with a stream on people to obtain Mary Smith
        Person result = people.stream().filter(person -> person.getFullName().equals("Mary Smith")).findFirst().get();

        Assertions.assertEquals("Mary", result.getFirstName());
        Assertions.assertEquals("Smith", result.getLastName());
    }

    @Test
    @Tag("KATA")
    @DisplayName("findPetNamedSerpy 🐍")
    public void findPetNamedSerpy()
    {
        //TODO
        // transform this into a list of pets from people
         List<Pet> petList = people.stream()
                 .map(person -> person.getPets())
                 .reduce((pets, pets2) -> {
                     List<Pet> newList = Stream.concat(pets.stream(), pets2.stream()).toList();
                     return newList;
                 }).get();

        //TODO
        // obtain serpySnake pet from petList
        Pet serpySnake = petList.stream().filter(pet -> pet.getName().equals("Serpy")).findAny().get();

        Assertions.assertEquals("🐍",serpySnake.getType().toString());
    }

    @Test
    @Tag("KATA")
    public void getPeopleWithPets()
    {
        //TODO
        // replace with only the pet owners
        List<Person> petPeople = this.people.stream().filter(person -> person.isPetPerson()).toList();

        Assertions.assertEquals(7, petPeople.size());
    }

    @Test
    @Tag("KATA")
    public void getAllPetTypesOfAllPeople()
    {
        Function<Person, Map<PetType, Long>> function = Person::getPetTypes;

        //TODO
        // use the previous function to obtain the set of pet types

        // Utiliza la función para obtener el conjunto de tipos de mascotas
        Set<PetType> petTypes = this.people.stream()
                .map(function)
                // Aplica la función directamente
                .flatMap(map -> map.keySet().stream())
                // Convierte los conjuntos de claves en un solo flujo
                .collect(Collectors.toSet());

        var expectedSet = Set.of(PetType.CAT, PetType.DOG, PetType.TURTLE, PetType.HAMSTER, PetType.BIRD, PetType.SNAKE);
        Assertions.assertEquals(expectedSet, petTypes);
    }

    @Test
    @Tag("KATA")
    public void getAllPetEmojisOfAllPeople()
    {
        Function<Person, Map<String, Long>> function = Person::getPetEmojis;

        //TODO
        // use the previous function to obtain the set of emojis
        Set<String> petEmojis = this.people.stream()
                .map(function)
                // Aplica la función directamente
                .flatMap(map -> map.keySet().stream())
                .peek(s -> System.out.println(s))
                // Convierte los conjuntos de claves en un solo flujo
                .collect(Collectors.toSet());

        var expected = Set.of("🐱", "🐶", "🐢", "🐹", "🐦", "🐍");
        Assertions.assertEquals(expected, petEmojis);
    }

    @Test
    @Tag("KATA")
    public void getFirstNamesOfAllPeople()
    {
        //TODO
        // transform this.people into a list of first names
        List<String> firstNames = this.people.stream()
                .map(person -> person.getFirstName())
                .toList();

        var expectedList = List.of("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John");
        Assertions.assertEquals(expectedList, firstNames);
    }

    @Test
    @Tag("KATA")
    @DisplayName("doAnyPeopleHaveCatsRefactor 🐱?")
    public void doAnyPeopleHaveCatsRefactor()
    {
        //TODO
        // test with a stream on people, if anyone has a cat at least
        boolean peopleHaveCatsLambda = this.people.stream().anyMatch(person -> person.hasPet(PetType.CAT));
        Assertions.assertTrue(peopleHaveCatsLambda);

    }

    @Test
    @Tag("KATA")
    @DisplayName("doAllPeopleHaveCatsRefactor 🐱?")
    public void doAllPeopleHaveCatsRefactor()
    {
        //TODO
        // test if all the people have cats
        boolean peopleHaveCats =  this.people.stream().allMatch(person -> person.hasPet(PetType.CAT));
        Assertions.assertFalse(peopleHaveCats);
    }

    @Test
    @Tag("KATA")
    @DisplayName("getPeopleWithCatsRefactor 🐱?")
    public void getPeopleWithCatsRefactor()
    {
        //TODO
        // obtain persons with cats
        List<Person> peopleWithCats = this.people.stream().filter(person -> person.hasPet(PetType.CAT)).toList();
        Assertions.assertEquals(2, peopleWithCats.size());
    }

    @Test
    @Tag("KATA")
    @DisplayName("getPeopleWithoutCatsRefactor 🐱?")
    public void getPeopleWithoutCatsRefactor()
    {
        //TODO
        // obtain persons without cats
        List<Person> peopleWithoutCats = this.people.stream().filter(person -> !person.hasPet(PetType.CAT)).toList();
        Assertions.assertEquals(6, peopleWithoutCats.size());
    }
}
