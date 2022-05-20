package com.uca.entity;

import java.sql.Date;

import static com.uca.util.IDUtil.requireValidId;
import static com.uca.util.StringUtil.requiredString;
import static java.util.Objects.requireNonNull;

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
        return this.id;
    }

    public void setId(long id)
    {
        this.id = requireValidId(id);
    }

    public Date getAttributionDate()
    {
        return (Date) this.attributionDate.clone();
    }

    public void setAttributionDate(Date attributionDate)
    {
        this.attributionDate = requireNonNull(attributionDate);
    }

    public String getMotive()
    {
        return this.motive;
    }

    public void setMotive(String motive)
    {
        this.motive = requiredString(motive);
    }

    public TeacherEntity getTeacher()
    {
        return new TeacherEntity(this.teacher);
    }

    public void setTeacher(TeacherEntity teacher)
    {
        this.teacher = requireNonNull(teacher);
    }

    public StickerEntity getSticker()
    {
        return new StickerEntity(this.sticker);
    }

    public void setSticker(StickerEntity sticker)
    {
        this.sticker = requireNonNull(sticker);
    }

    public StudentEntity getStudent()
    {
        return new StudentEntity(this.student);
    }

    public void setStudent(StudentEntity student)
    {
        this.student = requireNonNull(student);
    }

    @Override
    public String toString()
    {
        return "AwardEntity{" +
               "id=" + this.id +
               ", attributionDate=" + this.attributionDate +
               ", motive='" + this.motive + '\'' +
               ", teacher=" + this.teacher +
               ", sticker=" + this.sticker +
               ", student=" + this.student +
               '}';
    }
}
