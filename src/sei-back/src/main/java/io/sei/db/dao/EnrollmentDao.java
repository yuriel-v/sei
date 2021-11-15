package io.sei.db.dao;

import java.io.IOException;
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


    private Enrollment getEnrollment(User user, Subject subject) throws IOException
    {
        if (user == null || subject == null) {
            throw new IOException("Inexistent user or subject");
        }

        for (Enrollment enrollment : user.getEnrolledSubjects()) {
            if (enrollment.getSubject().getId() == subject.getId()) return enrollment;
        }
        return null;
    }

    public Enrollment getEnrollment(String registry, String subjectId) throws IOException
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        return this.getEnrollment(user, subject);
    }

    public boolean isEnrolled(String registry, String subjectId) throws IOException
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        return this.getEnrollment(user, subject) == null;
    }


    private void enroll(User user, Subject subject) throws IOException
    {
        if (this.isEnrolled(user.getRegistry(), subject.getId())) {
            this.getEnrollment(user, subject).reset();
        }
        else {
            user.getEnrolledSubjects().add(
                new Enrollment(subject)
            );
        }
    }

    public void enroll(String registry, String subjectId) throws IOException
    {
        User usr = U_DAO.findByRegistry(registry);
        Subject sbj = S_DAO.findById(subjectId);
        this.enroll(usr, sbj);
    }

    private void lock(User user, Subject subject) throws NotEnrolledException, IOException
    {
        Enrollment enroll = this.getEnrollment(user, subject);
        if (enroll == null) {
            throw new NotEnrolledException(null, user.getRegistry(), subject.getId(), subject.getName());
        }

        enroll.toggleLock();
    }

    public void lock(String registry, String subjectId) throws NotEnrolledException, IOException
    {
        User usr = U_DAO.findByRegistry(registry);
        Subject sbj = S_DAO.findById(subjectId);
        this.lock(usr, sbj);
    }

    public void update(String registry, String subjectId, ExamType examType, String mode, String newval)
        throws NotEnrolledException, IOException, ParseException
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
        else {
            throw new IOException(String.format("Unknown mode '%s'", mode));
        }
    }
}
