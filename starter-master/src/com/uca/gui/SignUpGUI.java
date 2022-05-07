package com.uca.gui;

import com.uca.util.GuiUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;


public class SignUpGUI
{
    public static String display() throws IOException, TemplateException
    {
        Template template = _FreeMarkerInitializer.getContext().getTemplate("auth/signup.ftl");
        return GuiUtil.render(template, null, new StringWriter());
    }
}