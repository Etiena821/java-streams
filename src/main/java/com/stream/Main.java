package com.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.stream.Gender.*;

public class Main {
    public static void main(String[] args) {

        List<Person> people = getPeople();

        // Imperative programming

        List<Person> females1 = new ArrayList<>();

        for (Person person : people) {
            if (FEMALE.equals(person.getGender())) {
                females1.add(person);
            }
        }

        females1.forEach(System.out::println);

        // *************** Filter

        List<Person> females = people.stream()
                .filter(person -> FEMALE.equals(person.getGender()))
                .collect(Collectors.toList());

        females.forEach(System.out::println);

         // *************** sort

        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
                .collect(Collectors.toList());

        sorted.forEach(System.out::println);

         // *************** All Match (this means that everyone on the list has an age bigger than 13)

        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 13);
        System.out.println(allMatch);


         // *************** Any Match (this means that at least on person in the list has an age is bigger than 13)

        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 140);
        System.out.println(anyMatch);

         // ************ None Match (This return true when on one on the list matches the inputted value)

        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Mallam"));
        System.out.println(noneMatch);


        // ****************** Max

        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);


        // ****************** Min

        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // ******************* Grouping

        Map<Gender, List<Person>> groupingByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupingByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });

        // Print out the name of the oldest person on the list

        Optional<String> oldestFemale = people.stream()
                .filter(person -> FEMALE.equals(person.getGender()))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);

        oldestFemale.ifPresent(System.out::println);

    }



    private static List<Person> getPeople() {

        return List.of(
                new Person("Steve Jobs", 42, MALE),
                new Person("Mary Jones", 20, FEMALE),
                new Person("Micheal Scoff", 90, MALE),
                new Person("Chidi Jones", 14, MALE),
                new Person("Chioma Jesus", 18, FEMALE),
                new Person("Cynthia Jesse", 120, FEMALE),
                new Person("Rose Mary", 60, FEMALE),
                new Person("Rita Adam", 45, FEMALE)
        );
    }

}
