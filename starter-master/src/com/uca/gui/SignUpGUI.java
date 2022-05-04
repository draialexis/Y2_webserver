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

public class SignUpGUI
{
    public static String display() throws IOException, TemplateException
    {
        Template template = _FreeMarkerInitializer.getContext().getTemplate("auth/signup.ftl");
        return _UtilGUI.inAndOut(template, null, new StringWriter());
    }

    public static String signUp(String firstName, String lastName, String userName, String userPwd)
            throws IOException, TemplateException
    {
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
            TeacherCore.create(teacher);
            input.put("status", "est maintenant inscrit");
        } catch (SQLException e)
        {
            if (e.getClass() == JdbcSQLIntegrityConstraintViolationException.class)
            {
                input.put("status", "ce nom d'utilisateur est d&eacute;j&agrave; pris !");
            }
            else
            {
                input.put("status", "un probl&egrave;me est survernu " +
                                    "&agrave; cause de ce nom d'utilisateur et/ou ce mot de passe");
                e.printStackTrace();
            }
        }
        return _UtilGUI.inAndOut(template, input, new StringWriter());
    }
}