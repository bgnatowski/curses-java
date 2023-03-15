import java.util.*;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Person> people = getPeople();

    // Imperative approach
//    List<Person> females = new ArrayList<>();
//
//    for(Person person : people){
//      if(person.getGender().equals(Gender.FEMALE))
//        females.add(person);
//    }
//
//    females.forEach(System.out::println);

    // Declarative approach

    // Filter
    people.stream()
            .filter(person -> person.getGender().equals(Gender.FEMALE))
            .collect(Collectors.toList())
            .forEach(System.out::println);
    System.out.println();
    // Sort
    people.stream()
            .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
            .collect(Collectors.toList())
            .forEach(System.out::println);
    System.out.println();
    // All match
    boolean allMatch = people.stream()
            .allMatch(person -> person.getAge() > 18);
    System.out.println("All match age > 18: " + allMatch);
    System.out.println();
    // Any match
    boolean anyMatch = people.stream()
            .anyMatch(person -> person.getAge() > 18);
    System.out.println("Any match age > 18: " + anyMatch);
    System.out.println();
    // None match
    boolean isAntonioOnList = people.stream().noneMatch(person -> person.getName().equals("Antonio"));
    System.out.println("Is Antonio on list: " + isAntonioOnList);
    boolean isJamesBondOnList = people.stream().noneMatch(person -> person.getName().equals("James Bond"));
    System.out.println("Is James Bond on list: " + isJamesBondOnList + "\n");
    // Max
    people.stream().max(Comparator.comparing(Person::getAge))
            .ifPresent(System.out::println);
    // Min
    people.stream().min(Comparator.comparing(Person::getAge))
            .ifPresent(System.out::println);
    // Group
    Map<Gender, List<Person>> groupByGender = people.stream()
            .collect(Collectors.groupingBy(Person::getGender));

//    groupByGender.forEach((gender, people1) ->{
//      System.out.println(gender);
//      people1.forEach(System.out::println);
//      System.out.println();
//    });

    people.stream()
            .filter(person -> person.getGender().equals(Gender.FEMALE))
            .max(Comparator.comparing(Person::getAge))
            .map(Person::getName)
            .ifPresent(System.out::println);
  }

  private static List<Person> getPeople() {
    return List.of(
        new Person("Antonio", 20, Gender.MALE),
        new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE),
        new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE)
    );
  }

}
