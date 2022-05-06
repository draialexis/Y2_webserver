package com.uca.entity;

import java.util.Date;
// TODO use java.sql.Date instead?

public class AwardEntity
{
    private TeacherEntity teacher;
    private StickerEntity sticker;
    private StudentEntity student;
    private Date          attributionDate;
    private String        motive;

    TeacherEntity getTeacher()
    {
        // TODO return a copy instead
        return teacher;
    }

    void setTeacher(TeacherEntity teacher)
    {
        this.teacher = teacher;
    }

    StickerEntity getSticker()
    {
        // TODO return a copy instead
        return sticker;
    }

    void setSticker(StickerEntity sticker)
    {
        this.sticker = sticker;
    }

    StudentEntity getStudent()
    {
        // TODO return a copy instead
        return student;
    }

    void setStudent(StudentEntity student)
    {
        this.student = student;
    }

    Date getAttributionDate()
    {
        // TODO return a copy instead ?
        return attributionDate;
    }

    void setAttributionDate(Date attributionDate)
    {
        this.attributionDate = attributionDate;
    }

    String getMotive()
    {
        return motive;
    }

    void setMotive(String motive)
    {
        this.motive = motive;
    }

    @Override
    public String toString()
    {
        return "AwardEntity{" +
               "teacher=" + teacher +
               ", sticker=" + sticker +
               ", student=" + student +
               ", attributionDate=" + attributionDate +
               ", motive='" + motive + '\'' +
               '}';
    }
}
