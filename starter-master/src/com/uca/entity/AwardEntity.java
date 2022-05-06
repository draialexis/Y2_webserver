package com.uca.entity;

import java.sql.Date;

public class AwardEntity
{
    private long          id;
    private Date          attributionDate;
    private String        motive;
    private TeacherEntity teacher;
    private StickerEntity sticker;
    private StudentEntity student;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Date getAttributionDate()
    {
        // TODO return a copy instead ?
        return attributionDate;
    }

    public void setAttributionDate(Date attributionDate)
    {
        this.attributionDate = attributionDate;
    }

    public String getMotive()
    {
        return motive;
    }

    public void setMotive(String motive)
    {
        this.motive = motive;
    }

    public TeacherEntity getTeacher()
    {
        // TODO return a copy instead
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher)
    {
        this.teacher = teacher;
    }

    public StickerEntity getSticker()
    {
        // TODO return a copy instead
        return sticker;
    }

    public void setSticker(StickerEntity sticker)
    {
        this.sticker = sticker;
    }

    public StudentEntity getStudent()
    {
        // TODO return a copy instead
        return student;
    }

    public void setStudent(StudentEntity student)
    {
        this.student = student;
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
