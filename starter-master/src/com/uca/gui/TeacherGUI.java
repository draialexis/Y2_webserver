package com.uca.gui;

import com.uca.core.TeacherCore;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TeacherGUI
{
    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input = new HashMap<>();
        input.put("teachers", TeacherCore.readAll());
        Template template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teachers.ftl");
        return _UtilGUI.inAndOut(template, input, new StringWriter());
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input = new HashMap<>();
        input.put("teacher", TeacherCore.readById(id));
        Template template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teacher.ftl");
        return _UtilGUI.inAndOut(template, input, new StringWriter());
    }

    public static String readByUserName(String userName) throws IOException, TemplateException
    {
        Map<String, Object> input = new HashMap<>();
        input.put("teacher", TeacherCore.readByUserName(userName));
        Template template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teacher.ftl");
        return _UtilGUI.inAndOut(template, input, new StringWriter());
    }
}
