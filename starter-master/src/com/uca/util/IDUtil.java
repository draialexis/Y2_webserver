package com.uca.util;

public class IDUtil
{
    public static void requireValidAndIdentical(long id1, long id2)
    {
        if (requireValid(id1) != id2)
        {
            throw new IllegalArgumentException(
                    String.format("IDs should be identical: %d != %d", id1, id2)
            );
        }
    }

    public static long requireValid(long id)
    {
        if (id <= 0)
        {
            throw new IllegalArgumentException(
                    String.format("ID should be strictly positive: %d <= 0", id)
            );
        }
        return id;
    }
}
