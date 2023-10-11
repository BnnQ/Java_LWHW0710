package me.bnnq.utils;

import java.util.function.Predicate;

public class StringUtilities
{
    public static int countSubstringOccurrences(String string, String substring)
    {
        int count = 0;
        int index = 0;
        while (true)
        {
            index = string.indexOf(substring, index);
            if (index == -1)
            {
                break;
            }
            count++;
            index += substring.length();
        }

        return count;
    }

    public static int countOccurrences(String string, Predicate<Character> predicate)
    {
        int count = 0;
        for (int i = 0; i < string.length(); i++)
        {
            if (predicate.test(string.charAt(i)))
            {
                count++;
            }
        }

        return count;
    }

}
