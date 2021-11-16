package io.sei.db.model;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZoneId;
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

    private int getSemester(YearMonth ym) {
        return ym.getYear() + ym.getMonthValue() < 7 ? 1 : 2;
    }

    public boolean isExpired()
    {
        // atual 2021.2 = 2021 + 2 = 2023
        // antiga 2021.1 = 2021 + 1 = 2022
        int currentSemester = this.getSemester(YearMonth.now());
        int registrationSemester = this.getSemester(
            YearMonth.from(this.registrationDate.toInstant()
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalDate())
        );
        return currentSemester > registrationSemester;
    }

    public Date getExpiration()
    {
        int curYear = YearMonth.now().getYear();
        int expMonth = YearMonth.now().getMonthValue() < 7 ? 6 : 12;
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(String.format("31/%d/%d", expMonth, curYear));
        }
        catch (Exception e) {  // should never happen, but just so the compiler doesn't bitch about "uncaught exception"
            return null;
        }
    }

    public boolean isLocked() {
        return this.locked || this.isExpired();
    }

    public Exam findExam(ExamType type)
    {
        for (Exam exam : this.exams) {
            if (exam.getType() == type) return exam;
        }
        return null;
    }

    public boolean isOngoing()
    {
        boolean av1Done = this.findExam(ExamType.AV1).getStatus() == ExamStatus.OK || this.findExam(ExamType.APS1).getStatus() == ExamStatus.OK;
        boolean av2Done = this.findExam(ExamType.AV2).getStatus() == ExamStatus.OK || this.findExam(ExamType.APS2).getStatus() == ExamStatus.OK;
        boolean av3Done = this.findExam(ExamType.AV3).getStatus() == ExamStatus.OK;

        return !((av1Done && (av2Done || av3Done)) || (av2Done && av3Done));
    }

    public double getAverage()
    {
        double[] grades = {
            this.findExam(ExamType.AV1).getGrade() + this.findExam(ExamType.APS1).getGrade(),
            this.findExam(ExamType.AV2).getGrade() + this.findExam(ExamType.APS2).getGrade(),
            this.findExam(ExamType.AV3).getGrade()
        };

        double lowest = grades[0];
        for (double grade : grades)
        {
            if (grade < lowest) {
                lowest = grade;
            }
        }

        return (grades[0] + grades[1] + grades[2] - lowest) / 2;
    }

    public double getFinalGrade() {
        return this.isOngoing() ? -1.0 : this.getAverage();
    }

    public String getSituation() {
        return this.isOngoing() ? "Em andamento" : (this.getAverage() < 7 ? "Reprovado" : "Aprovado");
    }

}
