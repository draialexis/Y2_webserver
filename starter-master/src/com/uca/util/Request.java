package com.uca.util;

public class Request {
    public static String getQueryUsername(spark.Request request) {
        return request.queryParams("username");
    }
    public static String getQueryPassword(spark.Request request) {
        return request.queryParams("password");
    }

    public static boolean clientAcceptsHtml(spark.Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }
}
