package com.uca.util;

public class IDUtil
{
    public static void requireValidAndIdenticalId(long id1, long id2)
    {
        if (requireValidId(id1) != id2)
        {
            throw new IllegalArgumentException(String.format("IDs should be identical: (%d != %d)", id1, id2));
        }
    }

    public static long requireValidId(long id)
    {
        if (notIsValidId(id))
        {
            throw new IllegalArgumentException(String.format("ID should be strictly positive: (%d <= 0)", id));
        }
        return id;
    }

    public static boolean notIsValidId(long id)
    {
        return id <= 0;
    }
}
