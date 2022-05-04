package com.uca.gui;


import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;



public class LoginGui extends _GenericGUI{
    public static String LoginPageGUI() throws TemplateException, IOException {
        Map<String, Object> input  = new HashMap<>();
        input.put("login message", "Welcome to The login page");
        Template template = configuration.getTemplate("Login/login.ftl");
        return inAndOut(template, input, new StringWriter());
    }

    public static String LoginFailedGUI() throws TemplateException, IOException {
        Map<String, Object> input  = new HashMap<>();
        input.put("login Failed", "wrong username or password");
        Template template = configuration.getTemplate("Login/LoginFailed.ftl");
        return inAndOut(template, input, new StringWriter());
    }
}
