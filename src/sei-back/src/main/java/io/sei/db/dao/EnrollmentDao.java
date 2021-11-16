package io.sei.db.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import io.sei.db.model.User;
import io.sei.exception.NotEnrolledException;
import io.sei.db.model.Enrollment;
import io.sei.db.model.Exam;
import io.sei.db.model.ExamStatus;
import io.sei.db.model.ExamType;
import io.sei.db.model.Subject;

public class EnrollmentDao 
{
    private static final UserDao U_DAO = new UserDao();
    private static final SubjectDao S_DAO = new SubjectDao();


    private Enrollment getEnrollment(User user, Subject subject)
    {
        for (Enrollment enrollment : user.getEnrolledSubjects()) {
            if (enrollment.getSubject().getId() == subject.getId()) return enrollment;
        }
        return null;
    }

    public Enrollment getEnrollment(String registry, String subjectId)
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        return this.getEnrollment(user, subject);
    }

    private boolean isEnrolled(User user, Subject subject) {
        return this.getEnrollment(user, subject) == null;
    }

    public boolean isEnrolled(String registry, String subjectId)
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        return this.getEnrollment(user, subject) == null;
    }


    private void enroll(User user, Subject subject)
    {
        if (this.isEnrolled(user, subject)) {
            this.getEnrollment(user, subject).reset();
        }
        else {
            user.getEnrolledSubjects().add(
                new Enrollment(subject)
            );
        }
    }

    public void enroll(String registry, String subjectId)
    {
        User usr = U_DAO.findByRegistry(registry);
        Subject sbj = S_DAO.findById(subjectId);
        this.enroll(usr, sbj);
    }

    private void lock(User user, Subject subject) throws NotEnrolledException
    {
        Enrollment enroll = this.getEnrollment(user, subject);
        if (enroll == null) {
            throw new NotEnrolledException(null, user.getRegistry(), subject.getId(), subject.getName());
        }

        enroll.toggleLock();
    }

    public void lock(String registry, String subjectId) throws NotEnrolledException
    {
        User usr = U_DAO.findByRegistry(registry);
        Subject sbj = S_DAO.findById(subjectId);
        this.lock(usr, sbj);
    }

    public void update(String registry, String subjectId, ExamType examType, String mode, String newval)
        throws NotEnrolledException, ParseException
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        Enrollment enroll = this.getEnrollment(user, subject);
        if (enroll == null) {
            throw new NotEnrolledException(null, user.getRegistry(), subject.getId(), subject.getName());
        }
        Exam exam = enroll.findExam(examType);

        if (mode.toLowerCase() == "grade") {
            exam.setGrade(Double.parseDouble(newval));
        }
        else if (mode.toLowerCase() == "status") {
            exam.setStatus(ExamStatus.valueOf(newval.toUpperCase()));
        }
        else if (mode.toLowerCase() == "deadline") {
            exam.setDeadline(new SimpleDateFormat("dd/MM/yyyy").parse(newval.replaceAll("-", "/")));
        }
    }
}
