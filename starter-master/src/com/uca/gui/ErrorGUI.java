package com.uca.gui;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

public class ErrorGUI extends _BasicGUI
{
    public static String displayUnknown(String exceptionMsg) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("errors/custom.ftl");
        input.put("code", HTTP_INTERNAL_ERROR);
        input.put("msg", "erreur non prise en charge, cela ne devrait pas arriver...\n" + exceptionMsg);
        return render(template, input, new StringWriter());
    }

    public static String display(int code, String msg) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("errors/custom.ftl");
        input.put("code", code);
        input.put("msg", msg);
        return render(template, input, new StringWriter());
    }
}
