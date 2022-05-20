package com.uca.util;

public class StringUtil
{
    public static final int VARCHAR_SIZE = 50;

    public static String requiredString(String string)
    {
        if (!isValidString(string))
        {
            throw new IllegalArgumentException("this string is not allowed to be null, empty, or blank");
        }
        return string;
    }

    public static String requiredOfSize(String string)
    {
        if (requiredString(string).length() > VARCHAR_SIZE)
        {
            throw new IllegalArgumentException(
                    String.format("this string should not be larger than %d characters", VARCHAR_SIZE)
            );
        }
        return string;
    }

    public static boolean isValidShortString(String string)
    {
        return isValidString(string) && string.length() <= 50;
    }

    public static boolean isValidString(String string)
    {
        return string != null && !string.isBlank();
    }
}
