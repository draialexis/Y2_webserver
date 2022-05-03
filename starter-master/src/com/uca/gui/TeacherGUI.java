package com.uca.gui;

import com.uca.core.TeacherCore;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TeacherGUI extends _GenericGUI
{
    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input  = new HashMap<>();
        Writer              output = new StringWriter();
        input.put("teachers", TeacherCore.readAll());
        Template template = configuration.getTemplate("teachers/teachers.ftl");
        return inAndOut(template, input, output);
    }

    public static String readByUserName(String userName) throws IOException, TemplateException
    {
        Map<String, Object> input  = new HashMap<>();
        Writer              output = new StringWriter();
        input.put("teacher", TeacherCore.readByUserName(userName));
        Template template = configuration.getTemplate("teachers/teacher.ftl");
        return inAndOut(template, input, output);
    }
}
