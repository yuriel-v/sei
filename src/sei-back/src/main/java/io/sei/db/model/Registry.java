package io.sei.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Registry 
{
    private Subject subject;
    private ArrayList<Exam> exams;
    private Date registrationDate;

    private Registry() {
        this.registrationDate = Calendar.getInstance().getTime();
        this.exams = new ArrayList<Exam>();

        for (ExamType typ : ExamType.values())
            this.exams.add(new Exam(typ, null));
    }

    public Registry(Subject subject)
    {
        this();
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
}
