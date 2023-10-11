package me.bnnq.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class FileUtilities
{
    public static String readAllText(String pathToFile) throws IOException
    {
        return new String(Files.readAllBytes(Paths.get(pathToFile)));
    }

    public static void replaceAllSubstringsTo(String filePath, String replacement, String... substrings) throws IOException
    {
        String content = readAllText(filePath);
        for (String substring : substrings)
            content = content.replaceAll(substring, replacement);

        Files.write(Paths.get(filePath), content.getBytes());
    }

    public static String[] findExceptLines(String firstFilePath, String secondFilePath) {
        Set<String> firstFileLines = new HashSet<>();
        Set<String> secondFileLines = new HashSet<>();

        try {
            firstFileLines = new HashSet<>(Files.readAllLines(Paths.get(firstFilePath)));
            secondFileLines = new HashSet<>(Files.readAllLines(Paths.get(secondFilePath)));
        } catch (IOException exception) {
            System.err.println("File reading error: " + exception.getMessage());
        }

        Set<String> finalSecondFileLines = secondFileLines;
        return firstFileLines.stream()
                .filter(line -> !finalSecondFileLines.contains(line))
                .toArray(String[]::new);
    }

    public static String findLongestLine(String filePath) throws IOException {
        var stream = Files.lines(Paths.get(filePath));
        var longestLine = stream.max(Comparator.comparingInt(String::length)).orElse(null);

        stream.close();
        return longestLine;
    }

}