package com.uca.gui;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class _UtilGUI
{
    static String inAndOut(Template t, Map<String, Object> in, Writer out) throws TemplateException, IOException
    {
        t.setOutputEncoding("UTF-8");
        t.process(in, out);
        return out.toString();
    }
}