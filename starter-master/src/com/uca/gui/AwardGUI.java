package com.uca.gui;

import com.uca.core.AwardCore;
import com.uca.core.StickerCore;
import com.uca.core.StudentCore;
import com.uca.core.TeacherCore;
import com.uca.entity.AwardEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.uca.util.IDUtil.notIsValidId;
import static com.uca.util.IDUtil.requireValidId;
import static com.uca.util.StringUtil.isValidShortString;
import static com.uca.util.StringUtil.isValidString;

public class AwardGUI extends _BasicGUI
{
    public static String create(String motive, String teacherUserName, long studentId, long stickerId)
            throws IOException, TemplateException, IllegalArgumentException
    {
        if (notIsValidId(stickerId) || notIsValidId(studentId))
        {
            throw new IllegalArgumentException(InfoMsg.ID_INVALIDE.name());
        }
        else
        {
            if (!isValidString(motive) || !isValidShortString(teacherUserName))
            {
                infoMsg = InfoMsg.CHAMPS_NON_POSTABLES;
            }
            else
            {
                AwardEntity award = new AwardEntity();
                award.setAttributionDate(new Date(new java.util.Date().getTime()));
                award.setMotive(motive);
                award.setTeacher(TeacherCore.readByUserName(teacherUserName));
                award.setStudent(StudentCore.readById(studentId));
                award.setSticker(StickerCore.readById(stickerId));
                infoMsg = AwardCore.create(award) != null ? InfoMsg.AJOUT_SUCCES : InfoMsg.AJOUT_ECHEC;
            }
        }
        return readAll(true);
    }

    /**
     * displays several - or all - awards, depending on {@code studentId}
     *
     * @param isAuthorized whether the user is authorized to access the resource
     * @param studentId    a student id (will trigger a reading <em>by student</em> if > 0)
     * @return a view that displays said awards
     */
    private static String readMany(boolean isAuthorized, long studentId, boolean isByStudent)
            throws IOException, TemplateException, NoSuchElementException, IllegalArgumentException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("awards/awards.ftl");
        input.put("isByStudent", isByStudent);

        if (!isByStudent)
        {
            input.put("awards", AwardCore.readAll());
        }
        else
        {
            requireValidId(studentId);
            input.put("awards", AwardCore.readByStudentId(studentId));
        }

        if (isAuthorized)
        {
            input.put("students", StudentCore.readAll());
            input.put("stickers", StickerCore.readAll());
        }
        input.put("isAuthorized", isAuthorized);
        return render(template, input, new StringWriter());
    }

    public static String readByStudentId(boolean isAuthorized, long studentId)
            throws IOException, TemplateException, NoSuchElementException, IllegalArgumentException
    {
        return readMany(isAuthorized, studentId, true);
    }

    public static String readAll(boolean isAuthorized) throws IOException, TemplateException, NoSuchElementException
    {
        return readMany(isAuthorized, 0, false);
    }

    public static String readById(boolean isAuthorized, long id)
            throws IOException, TemplateException, IllegalArgumentException, NoSuchElementException
    {
        requireValidId(id);
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("awards/award.ftl");

        AwardEntity award = AwardCore.readById(id);

        if (award == null)
        {
            throw new NoSuchElementException(InfoMsg.RESSOURCE_N_EXISTE_PAS.name());
        }
        input.put("award", award);
        input.put("isAuthorized", isAuthorized);
        return render(template, input, new StringWriter());
    }

    public static String deleteById(long id) throws IOException, TemplateException, IllegalArgumentException
    {
        requireValidId(id);
        AwardCore.deleteById(id);
        // we assume that the user was only able to access this function because it was authorized
        return readMany(true, 0, false);
    }
}
