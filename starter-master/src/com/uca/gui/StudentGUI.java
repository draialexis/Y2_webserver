package com.uca.gui;

import com.uca.core.StudentCore;
import com.uca.entity.StudentEntity;
import com.uca.util.GuiUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class StudentGUI
{
    public static String create(String firstName, String lastName)
            throws IOException, TemplateException
    {
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("pupils/pupils.ftl");
        Map<String, Object> input    = new HashMap<>();
        StudentEntity       student  = new StudentEntity();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setId(StudentCore.findLastId() + 1);
        // set the id of the new student the last one this way we don't need to auto increment
        input.put("user", student);
        input.put("students", StudentCore.readAll());
        if (StudentCore.create(student) != null)
        {
            input.put("status", "est maintenant inscrit");
        }
        return GuiUtil.render(template, input, new StringWriter());
    }

    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("students/students.ftl");

        input.put("students", StudentCore.readAll());
        return GuiUtil.render(template, input, new StringWriter());
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("students/student.ftl");

        input.put("student", StudentCore.readById(id));
        return GuiUtil.render(template, input, new StringWriter());
    }

    public static String update(StudentEntity student)
            throws IOException, TemplateException
    {

        Template            template = _FreeMarkerInitializer.getContext().getTemplate("pupils/pupils.ftl");
        Map<String, Object> input    = new HashMap<>();
        input.put("students", StudentCore.readAll());
        if (StudentCore.update(student, student.getId()) != null)
        {
            input.put("status", "est maintenant modifier");
        }
        return GuiUtil.render(template, input, new StringWriter());
    }

    public static String displayCreate() throws IOException, TemplateException
    {
        Template template = _FreeMarkerInitializer.getContext().getTemplate("pupils/createPupil.ftl");
        return GuiUtil.render(template, null, new StringWriter());
    }

    public static String displayModifPage(long id_stud) throws IOException, TemplateException
    {
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("pupils/modifPupil.ftl");
        Map<String, Object> input    = new HashMap<>();
        String              p        = String.valueOf(id_stud);
        input.put("pa", p);

        return GuiUtil.render(template, input, new StringWriter());
    }

}
