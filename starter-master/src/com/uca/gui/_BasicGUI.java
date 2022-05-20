package com.uca.gui;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public abstract class _BasicGUI
{
    static InfoMsg infoMsg;

    static void useAndResetStatus(Map<String, Object> input)
    {
        requireNonNull(input);
        input.put("status", infoMsg);
        infoMsg = null;
    }

    static String render(Template template, Map<String, Object> input, Writer output)
            throws TemplateException, IOException
    {
        useAndResetStatus(input);
        requireNonNull(output);
        requireNonNull(template);
        template.setOutputEncoding("UTF-8");
        template.process(input, output);
        return output.toString();
    }
}

