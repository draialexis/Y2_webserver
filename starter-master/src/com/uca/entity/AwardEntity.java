package com.uca.entity;

import com.uca.util.IDUtil;
import com.uca.util.StringUtil;

import java.sql.Date;
import java.util.Objects;

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
        this.id = IDUtil.requireValid(id);
    }

    public Date getAttributionDate()
    {
        return (Date) attributionDate.clone();
    }

    public void setAttributionDate(Date attributionDate)
    {
        this.attributionDate = Objects.requireNonNull(attributionDate);
    }

    public String getMotive()
    {
        return motive;
    }

    public void setMotive(String motive)
    {
        this.motive = StringUtil.required(motive);
    }

    public TeacherEntity getTeacher()
    {
        return new TeacherEntity(this.teacher);
    }

    public void setTeacher(TeacherEntity teacher)
    {
        this.teacher = Objects.requireNonNull(teacher);
    }

    public StickerEntity getSticker()
    {
        return new StickerEntity(this.sticker);
    }

    public void setSticker(StickerEntity sticker)
    {
        this.sticker = Objects.requireNonNull(sticker);
    }

    public StudentEntity getStudent()
    {
        return new StudentEntity(this.student);
    }

    public void setStudent(StudentEntity student)
    {
        this.student = Objects.requireNonNull(student);
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
