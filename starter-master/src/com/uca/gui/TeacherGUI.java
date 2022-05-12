package com.uca.gui;

import com.uca.core.TeacherCore;
import com.uca.entity.TeacherEntity;
import com.uca.util.Encryptor;
import com.uca.util.LoginUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.uca.util.IDUtil.isValidId;
import static com.uca.util.StringUtil.isValidShortString;

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
        if (!isValidShortString(firstName)
            || !isValidShortString(lastName)
            || !isValidShortString(userName)
            || !isValidShortString(userPwd)
            || !isValidShortString(userPwdValidation))
        {
            infoMsg = InfoMsg.CHAMPS_NON_POSTABLES;
        }
        else
        {
            if (userPwd.length() < LoginUtil.UNHASHED_PWD_SIZE_MIN ||
                userPwd.length() > LoginUtil.UNHASHED_PWD_SIZE_MAX)
            {
                infoMsg = InfoMsg.TAILLE_MOTS_DE_PASSE_NON_RESPECTEES;
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
                        teacher.setUserSalt(Encryptor.generateSalt(LoginUtil.SALT_SIZE));
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

    public static String readAll()
            throws IOException, TemplateException, NoSuchElementException
    {
        Map<String, Object>      input    = new HashMap<>();
        Template                 template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teachers.ftl");
        ArrayList<TeacherEntity>  teachers = TeacherCore.readAll();
        if (teachers.isEmpty())
        {
            throw new NoSuchElementException(InfoMsg.PAS_D_ENSEIGNANTS_CONTACTEZ_ADMIN.name());
        }
        input.put("teachers", TeacherCore.readAll());
        return render(template, input, new StringWriter());
    }

    public static String readById(long id)
            throws IOException, TemplateException, NoSuchElementException, IllegalArgumentException
    {
        if (!isValidId(id))
        {
            throw new IllegalArgumentException(InfoMsg.ID_INVALIDE.name());
        }
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teacher.ftl");
        TeacherEntity       teacher  = TeacherCore.readById(id);
        if (teacher == null)
        {
            throw new NoSuchElementException(InfoMsg.RESSOURCE_N_EXISTE_PAS.name());
        }
        input.put("teacher", teacher);
        return render(template, input, new StringWriter());
    }
}
