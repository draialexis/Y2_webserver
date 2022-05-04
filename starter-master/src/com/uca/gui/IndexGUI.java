package com.uca.gui;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

public class IndexGUI
{
    public static String display() throws IOException, TemplateException
    {
        Template template = _FreeMarkerInitializer.getContext().getTemplate("index.ftl");
        return _UtilGUI.inAndOut(template, null, new StringWriter());
    }
}
