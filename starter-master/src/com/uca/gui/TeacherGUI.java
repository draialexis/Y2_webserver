package com.uca.gui;

import com.uca.core.StickerCore;
import com.uca.core.TeacherCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TeacherGUI
{
    public static String readAll() throws IOException, TemplateException
    {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("teachers", TeacherCore.readAll());

        Writer   output   = new StringWriter();
        Template template = configuration.getTemplate("teachers/teachers.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String readByUserName(String userName) throws IOException, TemplateException
    {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("teacher", TeacherCore.readByUserName(userName));

        Writer   output   = new StringWriter();
        Template template = configuration.getTemplate("teachers/teacher.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
