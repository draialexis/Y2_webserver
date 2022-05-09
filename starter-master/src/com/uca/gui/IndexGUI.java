package com.uca.gui;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class IndexGUI extends _BasicGUI
{
    public static String display() throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("index.ftl");
        return render(template, input, new StringWriter());
    }
}
