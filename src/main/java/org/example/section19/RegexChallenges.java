package org.example.section19;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChallenges {
    public static void main(String[] args) {
        helloWorld("Hello, World!!");
        helloWorld("Hello, World!");
        System.out.println("------------------------------------------");
        sentenceComplex("The bike, is red!");
        sentenceComplex("hello World.");
        System.out.println("------------------------------------------");

        String emails = """
                john.boy@valid.com
                jane.doe-smith@valid.co.uk
                jane_Doe1976@valid.co.uk
                bob-1964@valid.net
                elaine@valid-test.com.au
                david@valid.io
                john.boy@invalid
                bob!@invalid.com
                elaineinvalid19883@.com
                david@invalid..com
                name@invalid.com.
                """;
        emailChecker(emails);

    }

    private static void helloWorld(String s) {
        System.out.println("'" + s + "' does" + (s.matches("Hello, World!") ? "" : " not") + " match 'Hello, World!'");
    }

    private static void sentence(String s) {
        System.out.println("'" + s + "' does" + (s.matches("[A-Z].*\\.") ? "" : " not") + " match");
    }

    private static void sentenceComplex(String s) {
        System.out.println("'" + s + "' does" + (s.matches("[A-Z].*[?!.]") ? "" : " not") + " match");
    }

    private static void emailChecker(String s) {
        Pattern pattern = Pattern.compile("([\\w.-]+)@[\\w-]+(?:\\.[\\w-]+)+\\v");
        Matcher matcher = pattern.matcher(s);
        matcher.results().forEach(mr -> System.out.println(mr.group(0).trim()));
    }

}
