package com.uca.gui;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

// https://binarycoders.dev/2015/03/22/spark-freemarker-basic-example/

public class IndexGUI extends _GenericGUI
{
    public static String display() throws IOException, TemplateException
    {
        Template template = configuration.getTemplate("index.ftl");
        return inAndOut(template, null, new StringWriter());
    }
}
