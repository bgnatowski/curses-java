package finalsection;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Lambdas {
    public static void main(String[] args) {
        Integer number = null;
        int counter = 0;

        BiFunction<String, Integer, String> upperCaseName = (name, age) -> {
            //logic
            if (name.isBlank()) throw new IllegalArgumentException("");
            System.out.println(age);
            return name.toUpperCase();


        };

        System.out.println(upperCaseName.apply("Bartek", 22));
    }




}
