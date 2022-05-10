package com.uca.gui;

import com.uca.core.StudentCore;
import com.uca.entity.StudentEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static com.uca.util.IDUtil.isValidId;
import static com.uca.util.StringUtil.isValidString;

public class StudentGUI extends _BasicGUI
{
    public static String create(String firstName, String lastName)
            throws IOException, TemplateException
    {
        if (!isValidString(firstName) || !isValidString(lastName))
        {
            infoMsg = InfoMsg.CHAMPS_VIDES_INTERDITS;
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
        if (!isValidId(id))
        {
            infoMsg = InfoMsg.ID_INVALIDE;
        }
        else
        {
            input.put("student", StudentCore.readById(id));
        }
        return render(template, input, new StringWriter());
    }

    public static String update(long id, String firstName, String lastName)
            throws IOException, TemplateException
    {
        if (!isValidId(id))
        {
            infoMsg = InfoMsg.ID_INVALIDE;
        }
        else
        {
            if (!isValidString(firstName) || !isValidString(lastName))
            {
                infoMsg = InfoMsg.CHAMPS_VIDES_INTERDITS;
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
        if (!isValidId(id))
        {
            infoMsg = InfoMsg.ID_INVALIDE;
        }
        else
        {
            StudentCore.deleteById(id);
        }
        return readAll();
    }
}
