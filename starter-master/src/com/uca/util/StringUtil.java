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

    /**
     * size is set by {@link StringUtil}.{@link #VARCHAR_SIZE}
     * <br>a string is valid and 'short' if it exists, is not empty, is not blank, and is not longer than max
     *
     * @param string string to validate
     * @return said string, if valid
     */
    public static String requiredShortString(String string)
    {
        if (!isValidShortString(string))
        {
            throw new IllegalArgumentException(
                    String.format("this string should not be larger than %d characters", VARCHAR_SIZE)
            );
        }
        return string;
    }

    public static boolean isValidShortString(String string)
    {
        return isValidString(string) && string.length() <= VARCHAR_SIZE;
    }

    public static boolean isValidString(String string)
    {
        return string != null && !string.isBlank();
    }
}
