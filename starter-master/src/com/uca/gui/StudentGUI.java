package com.uca.gui;

import com.uca.core.StudentCore;
import com.uca.entity.StudentEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class StudentGUI extends _BasicGUI
{
    public static String create(String firstName, String lastName)
            throws IOException, TemplateException
    {
        StudentEntity student = new StudentEntity();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        if (StudentCore.create(student) != null)
        {
            status = "ajout : succ&egrave;s";
        }
        return readAll();
    }

    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("students/students.ftl");

        input.put("students", StudentCore.readAll());
        return render(template, input, new StringWriter());
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("students/student.ftl");

        input.put("student", StudentCore.readById(id));
        return render(template, input, new StringWriter());
    }

    public static String update(long id, String firstName, String lastName)
            throws IOException, TemplateException
    {
        StudentEntity student = new StudentEntity();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        if (StudentCore.update(student, student.getId()) != null)
        {
            status = "modification : succ&egrave;s";
        }
        return readById(id);
    }

    public static String deleteById(long id) throws TemplateException, IOException
    {
        StudentCore.deleteById(id);
        status = "suppression : succ&egrave;s";
        System.out.println(status);
        return readAll();
    }
}
