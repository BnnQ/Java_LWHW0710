package me.bnnq;

import me.bnnq.models.Employee;
import me.bnnq.services.Corporation;
import me.bnnq.services.QueryableArray;
import me.bnnq.services.StringPagedList;
import me.bnnq.utils.*;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main
{
    public static void main(String[] args)
    {
        task12();
    }

    public static void task1()
    {
        String pathToFile = ScannerUtilities.scanString("Enter a path to file: ");
        try
        {
            String content = FileUtilities.readAllText(pathToFile);
            System.out.println(content);
        }
        catch (IOException exception)
        {
            System.out.println("File reading error: " + exception.getMessage());
        }
    }

    public static void task2()
    {
        String filePath = ScannerUtilities.scanString("Enter a path to file: ");
        String content;
        try
        {
            content = FileUtilities.readAllText(filePath);
        }
        catch (IOException exception)
        {
            System.err.println("File reading error: " + exception.getMessage());
            return;
        }

        int pageSize = ScannerUtilities.scanInt("Enter a page size: ");
        StringPagedList pagedList = new StringPagedList(content, pageSize);

        while (true)
        {
            ConsoleUtilities.clearConsole();
            printPage(pagedList);

            char input = Character.toUpperCase(ScannerUtilities.scanChar("Enter 'P' for previous page, 'N' for next page, and any other key for quit: "));

            switch (input)
            {
                case 'P':
                    pagedList.previousPage();
                    break;
                case 'N':
                    pagedList.nextPage();
                    break;
                default:
                    return;
            }
        }
    }

    private static void printPage(StringPagedList pagedList)
    {
        System.out.println("------------------ Page " + pagedList.getCurrentPage() + "/" + pagedList.getPageCount() + " ----------------------");
        System.out.println(pagedList.currentPageAsString());
        System.out.println("----------- <------- Page " + pagedList.getCurrentPage() + "/" + pagedList.getPageCount() + " -------> -----------");
    }

    public static void task3()
    {
        String filePath = ScannerUtilities.scanString("Enter a path to file: ");
        String content;
        try
        {
            content = FileUtilities.readAllText(filePath);
        }
        catch (IOException exception)
        {
            System.err.println("File reading error: " + exception.getMessage());
            return;
        }

        String substring = ScannerUtilities.scanString("Enter a substring: ");
        System.out.printf("The number of occurrences of the substring '%s' in the file '%s' is %d.\n", substring, filePath, StringUtilities.countSubstringOccurrences(content, substring));
    }

    public static void task4()
    {
        String filePath = ScannerUtilities.scanString("Enter a path to file: ");
        String content;
        try
        {
            content = FileUtilities.readAllText(filePath);
        }
        catch (IOException exception)
        {
            System.err.println("File reading error: " + exception.getMessage());
            return;
        }

        int numberOfLetters = StringUtilities.countOccurrences(content, Character::isLetter);
        int numberOfDigits = StringUtilities.countOccurrences(content, Character::isDigit);
        int numberOfPunctuationMarks = StringUtilities.countOccurrences(content, CharacterUtilities::isPunctuation);

        System.out.printf("File: %s\n", filePath);
        System.out.printf("Content: %s\n", content);
        System.out.printf("Number of letters: %d\n", numberOfLetters);
        System.out.printf("Number of digits: %d\n", numberOfDigits);
        System.out.printf("Number of punctuation marks: %d\n", numberOfPunctuationMarks);
    }

    public static void task5()
    {
        String filePath = ScannerUtilities.scanString("Enter a path to file: ");
        String content;
        try
        {
            content = FileUtilities.readAllText(filePath);
        }
        catch (IOException exception)
        {
            System.err.println("File reading error: " + exception.getMessage());
            return;
        }

        String substring = ScannerUtilities.scanString("Enter a substring: ");
        String replacement = ScannerUtilities.scanString("Enter a replacement: ");

        try
        {
            FileUtilities.replaceAllSubstringsTo(filePath, replacement, substring);
        }
        catch (IOException exception)
        {
            System.err.println("File writing error: " + exception.getMessage());
            return;
        }

        System.out.printf("The substring '%s' was replaced with '%s' in the file '%s'.\n", substring, replacement, filePath);
    }

    public static void task6()
    {
        String pathToFirstFile = ScannerUtilities.scanString("Enter a path to first file: ");
        String pathToSecondFile = ScannerUtilities.scanString("Enter a path to second file: ");
        String pathToThirdFile = ScannerUtilities.scanString("Enter a path to third file: ");
        String pathToResultFile = ScannerUtilities.scanString("Enter a path to result file: ");

        try
        {
            String resultContent = FileUtilities.readAllText(pathToFirstFile) + FileUtilities.readAllText(pathToSecondFile) + FileUtilities.readAllText(pathToThirdFile);
            Files.writeString(Paths.get(pathToResultFile), resultContent);
            System.out.printf("Successfully written to file %s.\n", pathToResultFile);
        }
        catch (IOException exception)
        {
            System.err.println("File writing error: " + exception.getMessage());
            return;
        }

    }

    public static void task7()
    {
        String[] bannedWords = ScannerUtilities.scanStringArray("Enter banned words separated by spaces: ");
        String filePath = ScannerUtilities.scanString("Enter a path to file: ");

        try
        {
            FileUtilities.replaceAllSubstringsTo(filePath, "<3", bannedWords);
            System.out.printf("Successfully removed banned words from file %s.\n", filePath);
        }
        catch (IOException exception)
        {
            System.err.println("File writing error: " + exception.getMessage());
            return;
        }
    }

    public static void task8()
    {
        String pathToFirstFile = ScannerUtilities.scanString("Enter a path to first file: ");
        String pathToSecondFile = ScannerUtilities.scanString("Enter a path to second file: ");

        String[] exceptLines = FileUtilities.findExceptLines(pathToFirstFile, pathToSecondFile);
        System.out.println("Lines that are in the first file but not in the second file:");
        for (String line : exceptLines)
            System.out.println(line);

        exceptLines = FileUtilities.findExceptLines(pathToSecondFile, pathToFirstFile);
        System.out.println("Lines that are in the second file but not in the first file:");
        for (String line : exceptLines)
            System.out.println(line);
    }

    public static void task9()
    {
String filePath = ScannerUtilities.scanString("Enter a path to file: ");
        String longestLine;
        try
        {
            longestLine = FileUtilities.findLongestLine(filePath);
        }
        catch (IOException exception)
        {
            System.err.println("File reading error: " + exception.getMessage());
            return;
        }

        System.out.printf("The longest line in the file '%s' is '%s' with length %d.\n", filePath, longestLine, longestLine.length());
    }

    public static void task10()
    {
        String filePath = ScannerUtilities.scanString("Enter a path to file: ");

        try
        {
            String content = FileUtilities.readAllText(filePath);
            String separator = ScannerUtilities.scanString("Enter a separator: ");
            var stream = Files.lines(Paths.get(filePath));

            stream.forEach(line -> {
                var array = ArrayUtilities.parseIntArray(line, separator);
                var queryableArray = new QueryableArray<>(array);
                System.out.printf("Array: %s\n", queryableArray);
                System.out.printf("Min: %d\n", queryableArray.min(Integer::compareTo));
                System.out.printf("Max: %d\n", queryableArray.max(Integer::compareTo));
                System.out.printf("Sum: %d\n", queryableArray.sum(Integer::intValue));
            });

            stream.close();
        }
        catch (IOException exception)
        {
            System.err.println("File reading error: " + exception.getMessage());
            return;
        }

    }

    public static void task11()
    {
        String filePath = ScannerUtilities.scanString("Enter a path to file: ");

        String arrayAsString = ScannerUtilities.scanString("Enter an array separated by whitespace: ");
        QueryableArray<Integer> array = new QueryableArray<>(ArrayUtilities.parseIntArray(arrayAsString, " "));

        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append(array).append("\n");

        var evenNumbers = array.search((element -> element % 2 == 0));
        contentBuilder.append(evenNumbers).append("\n");

        var oddNumbers = array.search((element -> element % 2 != 0));
        contentBuilder.append(oddNumbers).append("\n");

        var reversedNumbers = array.reverse();
        contentBuilder.append(reversedNumbers).append("\n");

        try
        {
            Files.writeString(Paths.get(filePath), contentBuilder.toString());
            System.out.printf("Successfully written to file %s.\n", filePath);
        }
        catch (IOException exception)
        {
            System.err.println("File writing error: " + exception.getMessage());
            return;
        }
    }

    public static void task12()
    {
        Corporation corporation = new Corporation();
        while (true)
        {
            ConsoleUtilities.clearConsole();
            System.out.println("1. Add employee");
            System.out.println("2. Edit employee");
            System.out.println("3. Remove employee");
            System.out.println("4. Find employee by fullname");
            System.out.println("5. Find employees by first letter of fullname");
            System.out.println("6. Save employees");
            System.out.println("7. Print employees");
            System.out.println("Any other number. Exit");

            String input = ScannerUtilities.scanString("Enter a number: ");
            ConsoleUtilities.clearConsole();
            switch (input)
            {
                case "1":
                {
                    String fullname = ScannerUtilities.scanString("Enter a fullname: ");
                    String position = ScannerUtilities.scanString("Enter a position: ");
                    int salary = ScannerUtilities.scanInt("Enter a salary: ");
                    corporation.addEmployee(new Employee(fullname, position, salary));
                    break;
                }
                case "2":
                {
                    String fullname = ScannerUtilities.scanString("Enter a fullname of employee to edit: ");

                    String newFullname = ScannerUtilities.scanString("Enter a new fullname: ");
                    String newPosition = ScannerUtilities.scanString("Enter a new position: ");
                    int newSalary = ScannerUtilities.scanInt("Enter a new salary: ");

                    corporation.editEmployee(fullname, new Employee(newFullname, newPosition, newSalary));
                    break;
                }
                case "3":
                {
                    String fullname = ScannerUtilities.scanString("Enter a fullname of employee to remove: ");
                    corporation.removeEmployee(fullname);
                    break;
                }
                case "4":
                {
                    String fullname = ScannerUtilities.scanString("Enter a fullname of employee to find: ");
                    corporation.printEmployees((employee -> employee.getFullname().equals(fullname)));
                    ConsoleUtilities.pause();
                    break;
                }
                case "5":
                {
                    char firstLetter = ScannerUtilities.scanChar("Enter a first letter of fullname: ");
                    corporation.printEmployees((employee -> employee.getFullname().charAt(0) == firstLetter));
                    ConsoleUtilities.pause();
                    break;
                }
                case "6":
                {
                    try
                    {
                        corporation.saveEmployees();
                    }
                    catch (IOException e)
                    {
                        System.err.println("File writing error: " + e.getMessage());
                    }
                    break;
                }
                case "7":
                {
                    corporation.printEmployees();
                    ConsoleUtilities.pause();
                    break;
                }
                default:
                {
                    try
                    {
                        corporation.saveEmployees();
                    }
                    catch (IOException e)
                    {
                        System.err.println("File writing error: " + e.getMessage());
                    }
                    return;
                }
            }

        }
    }

}