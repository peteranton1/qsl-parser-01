package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentifierParser {
    public static void main(String[] args) {
        String sourceCode = "int main_val = 42; String $name = \"Java\";";

        // Regex pattern for a valid Java identifier
        Pattern pattern = Pattern.compile("\\b[a-zA-Z_$][a-zA-Z0-9_$]*\\b");
        Matcher matcher = pattern.matcher(sourceCode);

        System.out.println("Found identifiers:");
        while (matcher.find()) {
            System.out.println("- " + matcher.group());
        }
    }
}
