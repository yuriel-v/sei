package io.sei.db.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import io.sei.db.model.User;
import io.sei.exception.AlreadyEnrolledException;
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


    public Enrollment getEnrollment(User user, Subject subject)
    {
        for (Enrollment enrollment : user.getAllEnrolledSubjects()) {
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

    public boolean isEnrolled(User user, Subject subject) {
        return this.getEnrollment(user, subject) == null;
    }

    public boolean isEnrolled(String registry, String subjectId)
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        return this.getEnrollment(user, subject) == null;
    }


    public void enroll(User user, Subject subject) throws AlreadyEnrolledException
    {
        if (this.isEnrolled(user, subject)) {
            if (this.getEnrollment(user, subject).isLocked()) {
                this.getEnrollment(user, subject).reset();
            }
            else {
                throw new AlreadyEnrolledException(null, user.getRegistry(), subject.getId(), subject.getName());
            }
        }
        else {
            user.getAllEnrolledSubjects().add(
                new Enrollment(subject)
            );
        }
    }

    public void enroll(String registry, String subjectId) throws AlreadyEnrolledException
    {
        User usr = U_DAO.findByRegistry(registry);
        Subject sbj = S_DAO.findById(subjectId);
        this.enroll(usr, sbj);
    }

    public void lock(User user, Subject subject) throws NotEnrolledException
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
        this.update(user, subject, examType, mode, newval);
    }

    public void update(User user, Subject subject, ExamType examType, String mode, String newval)
        throws NotEnrolledException, ParseException
    {
        Enrollment enroll = this.getEnrollment(user, subject);
        if (enroll == null) {
            throw new NotEnrolledException(null, user.getRegistry(), subject.getId(), subject.getName());
        }
        Exam exam = enroll.findExam(examType);

        if (mode.compareToIgnoreCase("grade") == 0) {
            exam.setGrade(Double.parseDouble(newval));
        }
        else if (mode.compareToIgnoreCase("status") == 0) {
            exam.setStatus(ExamStatus.valueOf(newval.toUpperCase()));
        }
        else if (mode.compareToIgnoreCase("deadline") == 0) {
            exam.setDeadline(new SimpleDateFormat("dd/MM/yyyy").parse(newval.replace('-', '/')));
        }
    }
}
