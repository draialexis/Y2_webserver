package com.uca.gui;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Objects;

public abstract class _BasicGUI
{
    static InfoMsg infoMsg;

    static void useAndResetStatus(Map<String, Object> input)
    {
        Objects.requireNonNull(input);
        input.put("status", infoMsg);
        infoMsg = null;
    }

    static String render(Template template, Map<String, Object> input, Writer output)
            throws TemplateException, IOException
    {
        useAndResetStatus(input);
        Objects.requireNonNull(output);
        template.setOutputEncoding("UTF-8");
        template.process(input, output);
        return output.toString();
    }
}

