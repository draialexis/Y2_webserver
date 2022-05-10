package com.uca.gui;

import com.uca.core.TeacherCore;
import com.uca.entity.TeacherEntity;
import com.uca.util.Encryptor;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static com.uca.util.StringUtil.isValidString;

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
        if (!isValidString(firstName)
            || !isValidString(lastName)
            || !isValidString(userName)
            || !isValidString(userPwd)
            || !isValidString(userPwdValidation))
        {
            infoMsg = InfoMsg.CHAMPS_VIDES_INTERDITS;
        }
        else
        {
            if (userPwd.length() < LoginHandler.UNHASHED_PWD_SIZE_MIN ||
                userPwd.length() > LoginHandler.UNHASHED_PWD_SIZE_MAX)
            {
                infoMsg = InfoMsg.TAILLE_MOT_DE_PASSE_NON_RESPECTEES;
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
                        teacher.setUserSalt(Encryptor.generateSalt(LoginHandler.SALT_SIZE));
                        teacher.setUserPwd(Encryptor.generateSecurePassword(userPwd, teacher.getUserSalt()));
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
