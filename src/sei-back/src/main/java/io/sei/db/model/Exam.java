package io.sei.db.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Exam 
{
    private ExamType id;
    private ExamStatus status;
    private double grade;
    private Date deadline;

    private Exam() {
        this.reset();
    }

    public Exam(ExamType id, Date deadline)
    {
        this();
        this.id = id;
        
        if (deadline != null)
            this.deadline = deadline;
    }

    public ExamType getId() {
        return id;
    }

    public ExamStatus getStatus() {
        return status;
    }

    public void setStatus(ExamStatus status) {
        this.status = status;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void reset()
    {
        this.status = ExamStatus.PENDING;
        this.grade = 0.0;
        // set to Dec 31st of the current year by default
        this.deadline = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 12, 31).getTime();
    }
}
