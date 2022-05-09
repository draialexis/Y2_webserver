package com.uca.gui;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginGUI extends _BasicGUI
{
    public static String display(InfoMsg msg) throws TemplateException, IOException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("auth/login.ftl");
        infoMsg = msg;
        return render(template, input, new StringWriter());
    }

    //todo add logout?
}
