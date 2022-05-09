package com.uca.gui;

import com.uca.core.AwardCore;
import com.uca.core.StickerCore;
import com.uca.core.StudentCore;
import com.uca.core.TeacherCore;
import com.uca.entity.AwardEntity;
import com.uca.util.GuiUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AwardGUI
{
    public static String create(String motive, String teacherUserName, long studentId, long stickerId)
            throws IOException, TemplateException, SQLException
    {
        AwardEntity award = new AwardEntity();
        award.setAttributionDate(new Date(new java.util.Date().getTime()));//TODO make customizable?
        award.setMotive(motive);
        award.setTeacher(TeacherCore.readByUserName(teacherUserName));
        award.setStudent(StudentCore.readById(studentId));
        award.setSticker(StickerCore.readById(stickerId));
        AwardCore.create(award);

        return readAll(true);
    }

    private static String readMany(boolean isAuthorized, long studentId) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("awards/awards.ftl");

        if (studentId == -1)
        {
            input.put("awards", AwardCore.readAll());
        }
        else
        {
            input.put("awards", AwardCore.readByStudentId(studentId));
        }
        input.put("isAuthorized", isAuthorized);
        if (isAuthorized)
        {
            input.put("stickers", StickerCore.readAll());
            input.put("students", StudentCore.readAll());
        }
        return GuiUtil.render(template, input, new StringWriter());
    }

    public static String readAll(boolean isAuthorized) throws IOException, TemplateException
    {
        return readMany(isAuthorized, -1);
    }

    public static String readByStudentId(boolean isAuthorized, long studentId) throws IOException, TemplateException
    {
        return readMany(isAuthorized, studentId);
    }

    public static String readById(boolean isAuthorized, long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("awards/award.ftl");

        input.put("award", AwardCore.readById(id));
        input.put("isAuthorized", isAuthorized);
        return GuiUtil.render(template, input, new StringWriter());
    }

    public static String deleteById(long id) throws IOException, TemplateException
    {
        AwardCore.deleteById(id);
        // we assume that the user was only able to access this function because it was authorized
        return readAll(true);
    }
}
