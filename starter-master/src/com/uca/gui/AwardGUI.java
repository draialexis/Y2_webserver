package com.uca.gui;

import com.uca.core.AwardCore;
import com.uca.core.StickerCore;
import com.uca.core.StudentCore;
import com.uca.core.TeacherCore;
import com.uca.entity.AwardEntity;
import com.uca.entity.StickerEntity;
import com.uca.entity.StudentEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.uca.util.IDUtil.isValidId;
import static com.uca.util.StringUtil.isValidShortString;
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
    private static String readMany(boolean isAuthorized, long studentId)
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
            if (!isValidId(studentId))
            {
                throw new IllegalArgumentException(InfoMsg.ID_INVALIDE.name());
            }
            else
            {
                ArrayList<AwardEntity> awards = AwardCore.readByStudentId(studentId);
                if (awards.isEmpty())
                {
                    throw new NoSuchElementException(InfoMsg.RESSOURCE_N_EXISTE_PAS.name());
                }
                input.put("awards", awards);
            }
        }
        if (isAuthorized)
        {
            ArrayList<StudentEntity> students = StudentCore.readAll();
            if (students.isEmpty())
            {
                throw new NoSuchElementException(InfoMsg.RESSOURCE_N_EXISTE_PAS.name());
            }
            input.put("students", students);
            ArrayList<StickerEntity> stickers = StickerCore.readAll();
            if (stickers.isEmpty())
            {
                throw new NoSuchElementException(InfoMsg.RESSOURCE_N_EXISTE_PAS.name());
            }
            input.put("stickers", stickers);
        }
        input.put("isAuthorized", isAuthorized);
        return render(template, input, new StringWriter());
    }

    public static String readByStudentId(boolean isAuthorized, long studentId)
            throws IOException, TemplateException, NoSuchElementException, IllegalArgumentException
    {
        isByStudent = true;
        return readMany(isAuthorized, studentId);
    }

    public static String readAll(boolean isAuthorized) throws IOException, TemplateException, NoSuchElementException
    {
        isByStudent = false;
        return readMany(isAuthorized, 0);
    }

    public static String readById(boolean isAuthorized, long id)
            throws IOException, TemplateException, IllegalArgumentException, NoSuchElementException
    {
        if (!isValidId(id))
        {
            throw new IllegalArgumentException(InfoMsg.ID_INVALIDE.name());
        }
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
