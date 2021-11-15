package io.sei.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Enrollment 
{
    private Subject subject;
    private ArrayList<Exam> exams;
    private Date registrationDate;
    private boolean locked;

    private Enrollment() {
        this.registrationDate = Calendar.getInstance().getTime();
        this.exams = new ArrayList<Exam>();
        this.locked = false;

        for (ExamType typ : ExamType.values())
            this.exams.add(new Exam(typ, null));
    }

    public Enrollment(Subject subject)
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

    public void reset() 
    {
        for (Exam exam : this.exams) {
            exam.reset();
        }
        this.registrationDate = Calendar.getInstance().getTime();
        this.locked = false;
    }

    public void toggleLock() {
        this.locked = !this.locked;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public Exam findExam(ExamType type)
    {
        for (Exam exam : this.exams) {
            if (exam.getId() == type) return exam;
        }
        return null;
    }
}
