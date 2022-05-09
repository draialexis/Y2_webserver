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
            infoMsg = InfoMsg.CHAMPS_VIDES_INTERDITS;
        }
        else
        {
            if (4 > userPwd.length() || userPwd.length() > 16)
            {
                infoMsg = InfoMsg.CONTRAINTES_MOT_DE_PASSE_NON_RESPECTEES;
            }
            else
            {
                if (!userPwd.equals(userPwdValidation))
                {
                    infoMsg = InfoMsg.MOT_DE_PASSE_CONFIRMATION_DIFFERENT;
                }
                else
                {
                    if (TeacherCore.readByUserName(userName) != null)
                    {
                        infoMsg = InfoMsg.NOM_UTILISATEUR_EXISTE_DEJA;
                    }
                    else
                    {
                        teacher.setFirstName(firstName);
                        teacher.setLastName(lastName);
                        teacher.setUserName(userName);
                        teacher.setUserSalt(_Encryptor.generateSalt(32));
                        teacher.setUserPwd(_Encryptor.generateSecurePassword(userPwd, teacher.getUserSalt()));
                        infoMsg = TeacherCore.create(teacher) != null
                                  ? InfoMsg.AJOUT_SUCCES
                                  : InfoMsg.AJOUT_ECHEC;
                    }
                }
            }
        }
        return readAll();
    }

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
}
