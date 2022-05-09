package com.uca.gui;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public abstract class _BasicGUI
{
    static InfoMsg infoMsg = null;

    static void useAndResetStatus(Map<String, Object> input)
    {
        input.put("status", infoMsg);
        infoMsg = null;
    }

    static String render(Template template, Map<String, Object> input, Writer output)
            throws TemplateException, IOException
    {
        useAndResetStatus(input);
        template.setOutputEncoding("UTF-8");
        template.process(input, output);
        return output.toString();
    }
}

