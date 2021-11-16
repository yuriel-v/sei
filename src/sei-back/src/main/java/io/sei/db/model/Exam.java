package io.sei.db.model;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

public class Exam 
{
    private ExamType type;
    private ExamStatus status;
    private double grade;
    private Date deadline;

    private Exam() {
        this.reset();
    }

    public Exam(ExamType type, Date deadline)
    {
        this();
        this.type = type;
        
        if (deadline != null)
            this.deadline = deadline;
    }

    public ExamType getType() {
        return type;
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
        int curYear = YearMonth.now().getYear();
        int endOfSemesterMonth = YearMonth.now().getMonthValue() < 7 ? 6 : 12;
        try {
            this.deadline = new SimpleDateFormat("dd/MM/yyyy").parse(String.format("31/%s/%s", endOfSemesterMonth, curYear));
        }
        catch (Exception e) {  // should never happen, but just so the compiler doesn't bitch about "unhandled exception"
            this.deadline = null;
        }
    }
}
