package com.uca.gui;

import com.uca.core.TeacherCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

// https://binarycoders.dev/2015/03/22/spark-freemarker-basic-example/

public class IndexGUI
{
    public static String display() throws IOException, TemplateException
    {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Writer   output   = new StringWriter();
        Template template = configuration.getTemplate("index.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(null, output);

        return output.toString();
    }
}
