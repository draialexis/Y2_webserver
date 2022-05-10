package com.uca.util;

public class StringUtil
{
    public static String required(String string)
    {
        if (string == null || string.isBlank())
        {
            throw new IllegalArgumentException("this string is not allowed to be null, empty, or blank");
        }
        return string;
    }
}
