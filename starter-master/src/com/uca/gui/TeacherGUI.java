package com.uca.gui;

import com.uca.core.TeacherCore;
import com.uca.dao._Encryptor;
import com.uca.entity.TeacherEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TeacherGUI
{
    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teachers.ftl");

        input.put("teachers", TeacherCore.readAll());
        return _UtilGUI.render(template, input, new StringWriter());
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teacher.ftl");

        input.put("teacher", TeacherCore.readById(id));
        return _UtilGUI.render(template, input, new StringWriter());
    }

    public static String readByUserName(String userName) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teacher.ftl");

        input.put("teacher", TeacherCore.readByUserName(userName));
        return _UtilGUI.render(template, input, new StringWriter());
    }

    public static String create(String firstName, String lastName, String userName, String userPwd)
            throws IOException, TemplateException
    {
        //TODO check not null, check size of userPwd between 4 and 16
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("auth/signup.ftl");
        Map<String, Object> input    = new HashMap<>();
        TeacherEntity       teacher  = new TeacherEntity();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setUserName(userName);
        teacher.setUserSalt(_Encryptor.generateSalt(32));
        teacher.setUserPwd(_Encryptor.generateSecurePassword(userPwd, teacher.getUserSalt()));
        input.put("user", teacher);
        try
        {
            if (TeacherCore.create(teacher) != null)
            {
                input.put("status", "est maintenant inscrit");
            }
        } catch (SQLException e)
        {
            if (e.getClass() == JdbcSQLIntegrityConstraintViolationException.class)
            {// the only constraint in this table
                input.put("status", "ce nom d'utilisateur est d&eacute;j&agrave; pris !");
            }
            else
            {
                input.put("status", "un probl&egrave;me est survernu " +
                                    "&agrave; cause de ce nom d'utilisateur et/ou ce mot de passe");
                e.printStackTrace();
            }
        }
        return _UtilGUI.render(template, input, new StringWriter());
    }
}
