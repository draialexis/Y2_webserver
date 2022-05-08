package com.uca.util;

import java.sql.Date;

public class DateUtil
{
    public static final int MS_IN_DAY = 86400000;

    public static Date getSQLDateRelativeToToday(long offsetInDays)
    {
        return new Date(new java.util.Date().getTime() + (offsetInDays * MS_IN_DAY));
    }
}
