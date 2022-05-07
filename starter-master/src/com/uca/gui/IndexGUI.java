package com.uca.gui;

import com.uca.util.GuiUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

public class IndexGUI
{
    public static String display() throws IOException, TemplateException
    {
        Template template = _FreeMarkerInitializer.getContext().getTemplate("index.ftl");
        return GuiUtil.render(template, null, new StringWriter());
    }
}
