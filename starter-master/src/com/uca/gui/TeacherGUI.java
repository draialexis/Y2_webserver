package com.uca.gui;

import com.uca.core.TeacherCore;
import com.uca.dao._Encryptor;
import com.uca.entity.TeacherEntity;
import com.uca.util.GuiUtil;
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
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teachers.ftl");

        input.put("teachers", TeacherCore.readAll());
        return GuiUtil.render(template, input, new StringWriter());
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teacher.ftl");

        input.put("teacher", TeacherCore.readById(id));
        return GuiUtil.render(template, input, new StringWriter());
    }

    public static String create(String firstName,
                                String lastName,
                                String userName,
                                String userPwd,
                                String userPwdValidation)
            throws IOException, TemplateException
    {
        String              status   = "status";
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("auth/signup.ftl");
        Map<String, Object> input    = new HashMap<>();
        TeacherEntity       teacher  = new TeacherEntity();
        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || userPwd.isEmpty())
        {
            input.put(status, "aucun des champs ne peut &ecirc;tre vide");
        }
        else
        {
            if (4 > userPwd.length() || userPwd.length() > 16)
            {
                input.put(status, "le mot de passe doit contenir 4 à 16 caract&egrave;res quelconques");
            }
            else
            {
                if (!userPwd.equals(userPwdValidation))
                {
                    input.put(status, "ce nom d'utilisateur est d&eacute;j&agrave; pris");
                }
                else
                {
                    teacher.setFirstName(firstName);
                    teacher.setLastName(lastName);
                    teacher.setUserName(userName);
                    teacher.setUserSalt(_Encryptor.generateSalt(32));
                    teacher.setUserPwd(_Encryptor.generateSecurePassword(userPwd, teacher.getUserSalt()));
                    input.put("user", teacher);

                    if (TeacherCore.create(teacher) != null)
                    {
                        input.put(status, "est maintenant inscrit");
                    }
                }
            }
        }
        return GuiUtil.render(template, input, new StringWriter());
    }
}
