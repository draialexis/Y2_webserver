package com.uca.util;

public class Request {
    public static String getQueryUsername(spark.Request request) {
        return request.queryParams("username");
    }
    public static String getQueryPassword(spark.Request request) {
        return request.queryParams("password");
    }
    public static String getQueryLoginRedirect(spark.Request request) {
        return request.queryParams("loginRedirect");
    }
}
