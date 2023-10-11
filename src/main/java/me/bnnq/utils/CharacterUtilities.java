package me.bnnq.utils;

public class CharacterUtilities
{
    public static boolean isPunctuation(char character)
    {
        return character == '.' || character == ',' || character == ':' || character == ';' || character == '!' || character == '?' || character == '-';
    }
}
