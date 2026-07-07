package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentsFun {
    static List<Match> commentMatches = new ArrayList<Match>();

    public static void main(String[] args) {
        Pattern commentsPattern = Pattern.compile("(//.*?$)|(/\\*.*?\\*/)", Pattern.MULTILINE | Pattern.DOTALL);
        Pattern stringsPattern = Pattern.compile("(\".*?(?<!\\\\)\")");

        String text = getTextFromFile("lib/src/main/java/org/example/CommentsFun.java");

        Matcher commentsMatcher = commentsPattern.matcher(text);
        while (commentsMatcher.find()) {
            Match match = new Match();
            match.start = commentsMatcher.start();
            match.text = commentsMatcher.group();
            commentMatches.add(match);
        }

        List<Match> commentsToRemove = new ArrayList<>();

        Matcher stringsMatcher = stringsPattern.matcher(text);
        while (stringsMatcher.find()) {
            for (Match comment : commentMatches) {
                if (comment.start > stringsMatcher.start() && comment.start < stringsMatcher.end())
                    commentsToRemove.add(comment);
            }
        }
        for (Match comment : commentsToRemove)
            commentMatches.remove(comment);

        for (Match comment : commentMatches)
            text = text.replace(comment.text, "!");

        System.out.println(text);
    }

    private static String getTextFromFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Single-line

    // "String? Nope"

    /*
     * "This  is not String either"
     */ static void dummy() {
         // empty
    }

    //Complex */
    ///*More complex*/

    /*Single line, but */

    String moreFun = " /* comment? doubt that */";

    String evenMoreFun = " // comment? doubt that ";

    static class Match {
        int start;
        String text;
    }
}