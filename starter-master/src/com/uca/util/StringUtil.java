package com.uca.util;

public class StringUtil
{
    public static final int VARCHAR_SIZE = 50;

    public static String required(String string)
    {
        if (string == null || string.isBlank())
        {
            throw new IllegalArgumentException("this string is not allowed to be null, empty, or blank");
        }
        return string;
    }

    public static String requiredOfSize(String string)
    {
        if (required(string).length() > VARCHAR_SIZE)
        {
            throw new IllegalArgumentException(
                    String.format("this string should not be larger than %d characters", VARCHAR_SIZE)
            );
        }
        return string;
    }
}
