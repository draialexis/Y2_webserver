package com.uca.gui;

import com.uca.core.TeacherCore;
import com.uca.dao._Encryptor;
import com.uca.entity.TeacherEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TeacherGUI extends _BasicGUI
{
    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teachers.ftl");

        input.put("teachers", TeacherCore.readAll());
        return render(template, input, new StringWriter());
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teacher.ftl");

        input.put("teacher", TeacherCore.readById(id));
        return render(template, input, new StringWriter());
    }

    public static String create(String firstName,
                                String lastName,
                                String userName,
                                String userPwd,
                                String userPwdValidation)
            throws IOException, TemplateException
    {
        TeacherEntity teacher = new TeacherEntity();
        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || userPwd.isEmpty())
        {
            infoMsg = "aucun des champs ne peut &ecirc;tre vide";
        }
        else
        {
            if (4 > userPwd.length() || userPwd.length() > 16)
            {
                infoMsg = "le mot de passe doit contenir 4 à 16 caract&egrave;res quelconques";
            }
            else
            {
                if (!userPwd.equals(userPwdValidation))
                {
                    infoMsg = "ce nom d'utilisateur est d&eacute;j&agrave; pris";
                }
                else
                {
                    teacher.setFirstName(firstName);
                    teacher.setLastName(lastName);
                    teacher.setUserName(userName);
                    teacher.setUserSalt(_Encryptor.generateSalt(32));
                    teacher.setUserPwd(_Encryptor.generateSecurePassword(userPwd, teacher.getUserSalt()));
                    if (TeacherCore.create(teacher) != null)
                    {
                        infoMsg = "ajout : succ&egrave;s";
                    }
                }
            }
        }
        return readAll();
    }
//    TODO use infoMsg everywhere
}
