package com.uca.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class GuiUtil
{
    public static String render(Template t, Map<String, Object> in, Writer out) throws TemplateException, IOException
    {
        t.setOutputEncoding("UTF-8");
        t.process(in, out);
        return out.toString();
    }
}