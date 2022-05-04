package com.uca.gui;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;


public class SignUpGUI
{
    public static String display() throws IOException, TemplateException
    {
        Template template = _FreeMarkerInitializer.getContext().getTemplate("auth/signup.ftl");
        return _UtilGUI.inAndOut(template, null, new StringWriter());
    }
}