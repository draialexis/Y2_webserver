package com.uca.util;

import java.sql.Date;

public class DateUtil
{
    public static final long MS_IN_SEC  = 1000;
    public static final long MS_IN_MIN  = MS_IN_SEC * 60;
    public static final long MS_IN_HOUR = MS_IN_MIN * 60;
    public static final long MS_IN_DAY  = MS_IN_HOUR * 24;

    public static Date getSQLDateRelativeToToday(long offsetInDays)
    {
        return new Date(new java.util.Date().getTime() + (offsetInDays * MS_IN_DAY));
    }
}
