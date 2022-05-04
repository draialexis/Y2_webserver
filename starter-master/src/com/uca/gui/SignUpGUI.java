package com.uca.gui;

import com.uca.core.TeacherCore;
import com.uca.entity.TeacherEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class SignUpGUI extends _GenericGUI
{
    public static String display() throws IOException, TemplateException
    {
        Template template = configuration.getTemplate("auth/signup.ftl");
        return inAndOut(template, null, new StringWriter());
    }

    public static String signUp(String firstName, String lastName, String userName, String userPwd)
            throws IOException, TemplateException
    {
        Template            template = configuration.getTemplate("auth/signup_status.ftl");
        Map<String, Object> input    = new HashMap<>();
        TeacherEntity       teacher  = new TeacherEntity();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setUserName(userName);
        teacher.setUserPwd(userPwd);
        input.put("user", teacher);
        try
        {
            TeacherCore.create(teacher);
            input.put("status", "est maintenant inscrit");
        } catch (Exception ignored)
        {
            input.put("status", ": ce nom d'utilisateur est d&eacute;j&agrave; pris !");
        }
        return inAndOut(template, input, new StringWriter());
    }
}
