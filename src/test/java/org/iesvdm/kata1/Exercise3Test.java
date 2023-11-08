package org.iesvdm.kata1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;


public class Exercise3Test extends PetDomainForKata
{
    @Test
    @Tag("KATA")
    public void getCountsByPetEmojis()
    {
        //TODO
        // Obtain petTypes from people
        List<PetType> petTypes = this.people.stream().map(person -> person.getPetTypes())
                .flatMap(map -> map.keySet().stream()).toList();

        // Do you recognize this pattern? Can you simplify it using Java Streams?
        Map<String, Long> petEmojiCounts = new HashMap<>();
        for (PetType petType : petTypes)
        {
            String petEmoji = petType.toString();
            Long count = petEmojiCounts.get(petEmoji);
            if (count == null)
            {
                count = 0L;
            }
            petEmojiCounts.put(petEmoji, count + 1L);
        }

        var expectedMap = Map.of("🐱", 2L, "🐶", 2L, "🐹", 2L, "🐍", 1L, "🐢", 1L, "🐦", 1L);
        Assertions.assertEquals(expectedMap, petEmojiCounts);

        //TODO
        // Replace by a stream the previous pattern
        Map<String, Long> petEmojiCounts2 = expectedMap;
        Assertions.assertEquals(expectedMap, petEmojiCounts2);

    }

    @Test
    @Tag("KATA")
    public void getPeopleByLastName()
    {

        // Do you recognize this pattern?
        Map<String, List<Person>> lastNamesToPeople = this.people.stream()
                .map(person -> {
                    List<Person> personList = new ArrayList<>();
                    personList.add(person);

                    Map<String, List<Person>> mapPeople = new HashMap<>();
                    mapPeople.put(person.getLastName(), personList);
                    return mapPeople;
                })
                .reduce(new HashMap<>(), (acc, val) -> acc.put(val.));

        for (Person person : this.people)
        {
            String lastName = person.getLastName();
            List<Person> peopleWithLastName = lastNamesToPeople.get(lastName);
            if (peopleWithLastName == null)
            {
                peopleWithLastName = new ArrayList<>();
                lastNamesToPeople.put(lastName, peopleWithLastName);
            }
            peopleWithLastName.add(person);
        }
        Assertions.assertEquals(3, lastNamesToPeople.get("Smith").size());


        //TODO
        // Replace by stream the previous pattern
        Map<String, List<Person>> lastNamesToPeople2 = new HashMap<>();
        Assertions.assertEquals(3, lastNamesToPeople2.get("Smith").size());
    }

    @Test
    @Tag("KATA")
    public void getPeopleByTheirPetTypes()
    {
        Map<PetType, Set<Person>> peopleByPetType = new HashMap<>();
        // Do you recognize this pattern? Is there a matching pattern for this in Java Streams?
        for (Person person : this.people)
        {
            List<Pet> pets = person.getPets();
            for (Pet pet : pets)
            {
                PetType petType = pet.getType();
                Set<Person> peopleWithPetType = peopleByPetType.get(petType);
                if (peopleWithPetType == null)
                {
                    peopleWithPetType = new HashSet<>();
                    peopleByPetType.put(petType, peopleWithPetType);
                }
                peopleWithPetType.add(person);
            }
        }

        Assertions.assertEquals(2, peopleByPetType.get(PetType.CAT).size());
        Assertions.assertEquals(2, peopleByPetType.get(PetType.DOG).size());
        Assertions.assertEquals(1, peopleByPetType.get(PetType.HAMSTER).size());
        Assertions.assertEquals(1, peopleByPetType.get(PetType.TURTLE).size());
        Assertions.assertEquals(1, peopleByPetType.get(PetType.BIRD).size());
        Assertions.assertEquals(1, peopleByPetType.get(PetType.SNAKE).size());

        //TODO
        // Replace by stream
        Map<PetType, Set<Person>> peopleByPetType2 = new HashMap<>();

        Assertions.assertEquals(2, peopleByPetType2.get(PetType.CAT).size());
        Assertions.assertEquals(2, peopleByPetType2.get(PetType.DOG).size());
        Assertions.assertEquals(1, peopleByPetType2.get(PetType.HAMSTER).size());
        Assertions.assertEquals(1, peopleByPetType2.get(PetType.TURTLE).size());
        Assertions.assertEquals(1, peopleByPetType2.get(PetType.BIRD).size());
        Assertions.assertEquals(1, peopleByPetType2.get(PetType.SNAKE).size());
    }

    @Test
    @Tag("KATA")
    public void getPeopleByTheirPetEmojis()
    {
        //TODO
        // Replace by stream
        Map<String, Set<Person>> petTypesToPeople = new HashMap<>();

        Assertions.assertEquals(2, petTypesToPeople.get("🐱").size());
        Assertions.assertEquals(2, petTypesToPeople.get("🐶").size());
        Assertions.assertEquals(1, petTypesToPeople.get("🐹").size());
        Assertions.assertEquals(1, petTypesToPeople.get("🐢").size());
        Assertions.assertEquals(1, petTypesToPeople.get("🐦").size());
        Assertions.assertEquals(1, petTypesToPeople.get("🐍").size());

    }
}
