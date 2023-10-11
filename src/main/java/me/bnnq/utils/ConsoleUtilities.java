package me.bnnq.utils;

public class ConsoleUtilities
{
    public static void clearConsole()
    {
        //if program runs in Intellij IDEA
        System.out.print("\n".repeat(100));

        //if program runs in cmd or powershell
        System.out.print("\033[H\033[2J");
    }

    public static void pause()
    {
        System.out.print("\nPress any key to continue...");
        ScannerUtilities.scanString();
    }
}
