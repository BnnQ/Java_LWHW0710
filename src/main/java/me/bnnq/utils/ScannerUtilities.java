package me.bnnq.utils;

import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ScannerUtilities
{
    private static Scanner scanner = new Scanner(System.in);

    public static int scanInt()
    {
        return Integer.parseInt(scanString());
    }

    public static int scanInt(String promptMessage)
    {
        System.out.print(promptMessage);
        return scanInt();
    }

    public static double scanDouble()
    {
        return Double.parseDouble(scanString());
    }

    public static double scanDouble(String promptMessage)
    {
        System.out.print(promptMessage);
        return scanDouble();
    }

    public static String scanString()
    {
        return scanner.nextLine();
    }

    public static String scanString(String promptMessage)
    {
        System.out.print(promptMessage);
        return scanString();
    }

    public static char scanChar()
    {
        return scanString().charAt(0);
    }

    public static char scanChar(String promptMessage)
    {
        System.out.print(promptMessage);
        return scanChar();
    }

    public static LocalDate scanDate(DateTimeFormatter formatter) throws ParseException
    {
        String dateString = scanString();
        return LocalDate.parse(dateString, formatter);
    }

    public static LocalDate scanDate(String promptMessage, DateTimeFormatter formatter) throws ParseException
    {
        System.out.print(promptMessage);
        return scanDate(formatter);
    }

    public static String[] scanStringArray(String promptMessage)
    {
        System.out.print(promptMessage);
        return scanString().split(" ");
    }

    public static String[] scanStringArray()
    {
        return scanStringArray("Enter strings separated by spaces: ");
    }

    /**
     * @param stream The stream to change to (default is System.in)
     */
    public static void changeStream(InputStream stream)
    {
        scanner = new Scanner(stream);
    }
}
