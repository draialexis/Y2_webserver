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

import static com.uca.util.IDUtil.isValidId;
import static com.uca.util.StringUtil.isValidString;

public class AwardGUI extends _BasicGUI
{
    private static boolean isByStudent;

    public static String create(String motive, String teacherUserName, long studentId, long stickerId)
            throws IOException, TemplateException
    {
        if (!isValidId(stickerId) || !isValidId(studentId))
        {
            infoMsg = InfoMsg.ID_INVALIDE;
        }
        else
        {
            if (!isValidString(motive) || !isValidString(teacherUserName))
            {
                infoMsg = InfoMsg.CHAMPS_VIDES_INTERDITS;
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
     * displays several or all awards, depending on {@code studentId}
     *
     * @param isAuthorized whether the user is authorized to access the resource
     * @param studentId    a student id (will trigger a reading <em>by student</em> if > 0)
     * @return a view that displays said awards
     * @throws IOException
     * @throws TemplateException
     */
    private static String readMany(boolean isAuthorized, long studentId)
            throws IOException, TemplateException
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
            if (!isValidId(studentId))
            {
                infoMsg = InfoMsg.ID_INVALIDE;
                isByStudent = false;
                return readMany(isAuthorized, studentId);
            }
            else
            {
                input.put("awards", AwardCore.readByStudentId(studentId));
            }
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
            throws IOException, TemplateException
    {
        isByStudent = true;
        return readMany(isAuthorized, studentId);
    }

    public static String readAll(boolean isAuthorized) throws IOException, TemplateException
    {
        isByStudent = false;
        return readMany(isAuthorized, 0);
    }

    public static String readById(boolean isAuthorized, long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("awards/award.ftl");

        if (!isValidId(id))
        {
            infoMsg = InfoMsg.ID_INVALIDE;
        }
        else
        {
            input.put("award", AwardCore.readById(id));
            input.put("isAuthorized", isAuthorized);
        }
        return render(template, input, new StringWriter());
    }

    public static String deleteById(long id) throws IOException, TemplateException
    {
        if (!isValidId(id))
        {
            infoMsg = InfoMsg.ID_INVALIDE;
        }
        else
        {
            AwardCore.deleteById(id);
        }
        // we assume that the user was only able to access this function because it was authorized
        isByStudent = false;
        return readMany(true, 0);
    }
}
