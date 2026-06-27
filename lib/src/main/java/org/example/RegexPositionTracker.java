package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class RegexPositionTracker {

    public static void main(String[] args) {
        String input = "The quick brown fox jumps over the 3 lazy dogs at exactly 5 PM.";

        // Define a list of regex patterns
        List<String> regexPatterns = new ArrayList<>();
        regexPatterns.add("\\b\\d+\\b"); // Matches any number
        regexPatterns.add("[A-Z][a-z]*"); // Matches any word starting with a capital letter
        regexPatterns.add("\\bfox\\b");   // Matches the exact word "fox"

        for (String regex : regexPatterns) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            System.out.println("Searching for pattern: \"" + regex + "\"");

            boolean found = false;
            // Iterate through all occurrences of the pattern in the string
            while (matcher.find()) {
                found = true;
                System.out.println("  - Found \"" + matcher.group() +
                    "\" at position: " + matcher.start());
            }

            if (!found) {
                System.out.println("  - No matches found.");
            }
        }
    }
}
