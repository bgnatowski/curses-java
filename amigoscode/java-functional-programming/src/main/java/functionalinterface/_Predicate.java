package functionalinterface;

import java.util.function.Predicate;

public class _Predicate {
    public static void main(String[] args) {
        String phoneNumber = "07123456789";
        String phoneNumber2 = "09345678";

        System.out.println("Without predicate");
        System.out.println(isPhoneNumberValid(phoneNumber));
        System.out.println(isPhoneNumberValid(phoneNumber2));

        System.out.println("With predicate");
        System.out.println(isPhoneNumberValidPredicate.test(phoneNumber));
        System.out.println(isPhoneNumberValidPredicate.test(phoneNumber2));

        System.out.println(
                "Is phone number valid and contains number 3 = " +
                isPhoneNumberValidPredicate
                        .or(containsNumber3)
                        .test(phoneNumber)
        );

        System.out.println(
                "Is phone number valid and contains number 3 = " +
                        isPhoneNumberValidPredicate
                                .or(containsNumber3)
                                .test(phoneNumber2)
        );
    }

    static Predicate<String> isPhoneNumberValidPredicate =
            phoneNumber -> phoneNumber.startsWith("07") & phoneNumber.length() == 11;

    static boolean isPhoneNumberValid(String phoneNumber){
        return phoneNumber.startsWith("07") & phoneNumber.length() == 11;
    }

    static Predicate<String> containsNumber3 =
            phoneNumber -> phoneNumber.contains("3");


}
