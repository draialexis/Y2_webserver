package com.uca.gui;

import com.uca.core.StudentCore;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class StudentGUI
{
    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("students/student.ftl");

        input.put("students", StudentCore.readAll());
        return _UtilGUI.render(template, input, new StringWriter());
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("students/students.ftl");

        input.put("student", StudentCore.readById(id));
        return _UtilGUI.render(template, input, new StringWriter());
    }
}
