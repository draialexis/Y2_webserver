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

public class AwardGUI extends _BasicGUI
{
    public static String create(String motive, String teacherUserName, long studentId, long stickerId)
            throws IOException, TemplateException
    {
        AwardEntity award = new AwardEntity();
        award.setAttributionDate(new Date(new java.util.Date().getTime()));
        award.setMotive(motive);
        award.setTeacher(TeacherCore.readByUserName(teacherUserName));
        award.setStudent(StudentCore.readById(studentId));
        award.setSticker(StickerCore.readById(stickerId));
        infoMsg = AwardCore.create(award) != null ? InfoMsg.AJOUT_SUCCES : InfoMsg.AJOUT_ECHEC;

        return readAll(true);
    }

    public static String readAll(boolean isAuthorized) throws IOException, TemplateException
    {
        return readMany(isAuthorized, -1);
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

        if (studentId <= 0)
        {
            input.put("awards", AwardCore.readAll());
            input.put("isByStudent", false);
        }
        else
        {
            input.put("isByStudent", true);
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
            throws IOException, TemplateException
    {
        return readMany(isAuthorized, studentId);
    }

    public static String readById(boolean isAuthorized, long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("awards/award.ftl");

        input.put("award", AwardCore.readById(id));
        input.put("isAuthorized", isAuthorized);
        return render(template, input, new StringWriter());
    }

    public static String deleteById(long id) throws IOException, TemplateException
    {
        AwardCore.deleteById(id);
        // we assume that the user was only able to access this function because it was authorized
        return readMany(true, -1);
    }
}
