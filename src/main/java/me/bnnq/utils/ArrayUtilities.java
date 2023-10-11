package me.bnnq.utils;

public class ArrayUtilities
{
    public static Integer[] parseIntArray(String arrayAsString, String separator)
    {
        String[] stringArray = arrayAsString.split(separator);
        Integer[] intArray = new Integer[stringArray.length];

        for (int i = 0; i < stringArray.length; i++)
            intArray[i] = Integer.parseInt(stringArray[i]);

        return intArray;
    }

}
