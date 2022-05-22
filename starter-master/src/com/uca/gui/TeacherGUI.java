package com.uca.gui;

import com.uca.core.TeacherCore;
import com.uca.entity.TeacherEntity;
import com.uca.util.Encryptor;
import com.uca.util.PropertiesReader;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.uca.util.IDUtil.requireValidId;
import static com.uca.util.StringUtil.isValidShortString;

public class TeacherGUI extends _BasicGUI
{
    private static final int UNHASHED_PWD_SIZE_MAX = 16;
    private static final int UNHASHED_PWD_SIZE_MIN = 4;
    private static final int SALT_SIZE             = 32;

    public static String create(String lastName,
                                String firstName,
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
            if (userPwd.length() < UNHASHED_PWD_SIZE_MIN ||
                userPwd.length() > UNHASHED_PWD_SIZE_MAX)
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
                        teacher.setUserSalt(Encryptor.generateSalt(SALT_SIZE));
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
        ArrayList<TeacherEntity> teachers = TeacherCore.readAll();
        if (teachers.isEmpty())
        {
            throw new NoSuchElementException(InfoMsg.PAS_D_ENSEIGNANTS_CONTACTEZ_ADMIN.name());
        }
        input.put("teachers", teachers);
        input.put("admin1", new PropertiesReader().getProperty("admin1"));
        input.put("admin2", new PropertiesReader().getProperty("admin2"));
        return render(template, input, new StringWriter());
    }

    public static String readById(long id)
            throws IOException, TemplateException, NoSuchElementException, IllegalArgumentException
    {
        requireValidId(id);
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("teachers/teacher.ftl");
        TeacherEntity       teacher  = TeacherCore.readById(id);
        if (teacher == null)
        {
            throw new NoSuchElementException(InfoMsg.RESSOURCE_N_EXISTE_PAS.name());
        }
        input.put("teacher", teacher);
        input.put("admin1", new PropertiesReader().getProperty("admin1"));
        input.put("admin2", new PropertiesReader().getProperty("admin2"));
        return render(template, input, new StringWriter());
    }

    public static String deleteById(long id) throws TemplateException, IOException
    {
        requireValidId(id);
        if (TeacherCore.isAdmin(id))
        {
            infoMsg = InfoMsg.IMPOSSIBLE_DE_SUPPRIMER_ADMIN;
        }
        else
        {
            TeacherCore.deleteById(id);
            infoMsg = InfoMsg.SUPPRESSION_SUCCES;
        }
        return readAll();
    }
}
