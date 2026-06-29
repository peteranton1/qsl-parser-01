package org.example;

/*
Unicode code point: 0 .. 1114111
 UTF-16 code unit : 0 ..   65535

surrogate pair
high surrogate: d800 .. dbff    110110 __________
 low surrogate: dc00 .. dfff    110111 __________
 */

public class PalindromeUtf {

    private static void is(String s) {
        if (!isPalindrome(s)) {
            System.out.println("\tis a palindrome string, " +
                "but isPalindrome claims it is not : " + s);
        }
    }

    private static void not(String s) {
        if (isPalindrome(s)) {
            System.out.println("\tnot a palindrome string, " +
                "but isPalindrome claims it is : " + s);
        }
    }

    static void main() {
        is("");
        is("?");
        is("!!");
        is("eve");
        is("anna");
        is("rotor");

        not("banana");

        is("\uD834\uDD1Elol\uD834\uDD1E");
    }

    private static boolean isPalindrome(String s) {
        for (int i = 0, k = s.length() - 1; i < k; i++, k--) {
            if (!areEqualChars(s, i, k)) {
                return false;
            }
        }
        return true;
    }

    private static boolean areEqualChars(String s, int i, int k) {
        char a = s.charAt(i);
        if (Character.isHighSurrogate(a)) {
            --k;
        } else if (Character.isLowSurrogate(a)) {
            ++k;
        }
        char b = s.charAt(k);
        return a == b;
    }
}
