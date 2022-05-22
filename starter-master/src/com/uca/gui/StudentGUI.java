package com.uca.gui;

import com.uca.core.StudentCore;
import com.uca.entity.StudentEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.uca.util.IDUtil.requireValidId;
import static com.uca.util.StringUtil.isValidShortString;

public class StudentGUI extends _BasicGUI
{
    public static String create(String lastName, String firstName)
            throws IOException, TemplateException
    {
        if (!isValidShortString(firstName) || !isValidShortString(lastName))
        {
            infoMsg = InfoMsg.CHAMPS_NON_POSTABLES;
        }
        else
        {
            StudentEntity student = new StudentEntity();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            infoMsg = StudentCore.create(student) != null ? InfoMsg.AJOUT_SUCCES : InfoMsg.AJOUT_ECHEC;
        }

        return readAll();
    }

    public static String readAll() throws IOException, TemplateException, NoSuchElementException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("students/students.ftl");

        input.put("students", StudentCore.readAll());
        return render(template, input, new StringWriter());
    }

    public static String readById(long id)
            throws IOException, TemplateException, IllegalArgumentException, NoSuchElementException
    {
        requireValidId(id);
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("students/student.ftl");

        StudentEntity student = StudentCore.readById(id);
        if (student == null)
        {
            throw new NoSuchElementException(InfoMsg.RESSOURCE_N_EXISTE_PAS.name());
        }
        input.put("student", student);
        return render(template, input, new StringWriter());
    }

    public static String update(long id, String lastName, String firstName)
            throws IOException, TemplateException, IllegalArgumentException
    {
        requireValidId(id);
        {
            if (!isValidShortString(firstName) || !isValidShortString(lastName))
            {
                infoMsg = InfoMsg.CHAMPS_NON_POSTABLES;
            }
            else
            {
                StudentEntity student = new StudentEntity();
                student.setId(id);
                student.setFirstName(firstName);
                student.setLastName(lastName);
                infoMsg = StudentCore.update(student, id) != null ? InfoMsg.MODIFICATION_SUCCES
                                                                  : InfoMsg.MODIFICATION_ECHEC;
            }
        }
        return readById(id);
    }

    public static String deleteById(long id) throws TemplateException, IOException
    {
        requireValidId(id);
        StudentCore.deleteById(id);
        infoMsg = InfoMsg.SUPPRESSION_SUCCES;
        return readAll();
    }
}
