package io.sei.api.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

import io.sei.db.dao.EnrollmentDao;
import io.sei.db.dao.SubjectDao;
import io.sei.db.dao.UserDao;
import io.sei.db.model.ExamStatus;
import io.sei.db.model.ExamType;
import io.sei.db.model.Subject;
import io.sei.db.model.User;
import io.sei.exception.AlreadyEnrolledException;
import io.sei.exception.NotEnrolledException;

public class Agendactl
{
    private static final EnrollmentDao E_DAO = new EnrollmentDao();
    private static final UserDao U_DAO = new UserDao();
    private static final SubjectDao S_DAO = new SubjectDao();

    public Agendactl() { }

    public List<String> call(String action, String registry, HashMap<String, String> data)
    {
        if (action.compareTo("enroll") == 0) {
            return this.enroll(registry, data.get("subjectId"));
        }
        else if (action.compareTo("lock") == 0) {
            return this.lock(registry, data.get("subjectId"));
        }
        else {
            ExamType exam;
            try {
                exam = ExamType.valueOf(data.get("exam").trim().toUpperCase());
            }
            catch (Exception e) {
                return Arrays.asList("400", String.format("unknown exam type: '%s'", data.get("exam")));
            }

            if (action.compareTo("grade") == 0) {
                return this.setGrade(
                    registry, data.get("subjectId"),
                    exam, data.get("grade")
                );
            }
            else if (action.compareTo("status") == 0) {
                return this.setStatus(
                    registry, data.get("subjectId"),
                    exam, data.get("status").toUpperCase()
                );
            }
            else if (action.compareTo("deadline") == 0) {
                return this.setDeadline(
                    registry, data.get("subjectId"),
                    exam, data.get("deadline")
                );
            }
            else {
                return Arrays.asList("0", String.format("action unknown: '%s'", action));
            }
        }
    }

    public List<String> enroll(String registry, String subjectId)
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        
        if (user == null) {
            return Arrays.asList("400", "inexistent user");
        }
        if (subject == null) {
            return Arrays.asList("400", "inexistent subject");
        }

        try {
            E_DAO.enroll(user, subject);
        }
        catch (AlreadyEnrolledException e) {
            return Arrays.asList("409", e.getMessage());
        }
        catch (Exception e) {
            return Arrays.asList("500", e.getMessage());
        }
        return Arrays.asList("201", "created");
    }

    public List<String> lock(String registry, String subjectId)
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        
        if (user == null) {
            return Arrays.asList("400", "inexistent user");
        }
        if (subject == null) {
            return Arrays.asList("400", "inexistent subject");
        }

        try {
            E_DAO.lock(user, subject);
        }
        catch (NotEnrolledException e) {
            return Arrays.asList("400", "inexistent enrollment");
        }
        catch (Exception e) {
            return Arrays.asList("500", e.getMessage());
        }
        return Arrays.asList("200", "locked");
    }

    public List<String> setGrade(String registry, String subjectId, ExamType exam, String grade)
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        
        if (user == null) {
            return Arrays.asList("400", "inexistent user");
        }
        if (subject == null) {
            return Arrays.asList("400", "inexistent subject");
        }

        try
        {
            @SuppressWarnings("unused")
            double realGrade = Double.parseDouble(grade);  // just to see if it can be cast to double
            E_DAO.update(user, subject, exam, "grade", grade);
        }
        catch (NumberFormatException e) {
            return Arrays.asList("400", "grade not convertible to double");
        }
        catch (NotEnrolledException e) {
            return Arrays.asList("400", e.getMessage());
        }
        catch (Exception e) {
            return Arrays.asList("500", e.getMessage());
        }
        return Arrays.asList("200", "grade changed");
    }

    public List<String> setStatus(String registry, String subjectId, ExamType exam, String status)
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        
        if (user == null) {
            return Arrays.asList("400", "inexistent user");
        }
        if (subject == null) {
            return Arrays.asList("400", "inexistent subject");
        }

        try
        {
            ExamStatus realStatus = ExamStatus.valueOf(status);
            E_DAO.update(user, subject, exam, "status", realStatus.toString());
        }
        catch (IllegalArgumentException e) {
            return Arrays.asList("400", "status not convertible to ExamStatus enum");
        }
        catch (NotEnrolledException e) {
            return Arrays.asList("400", e.getMessage());
        }
        catch (Exception e) {
            return Arrays.asList("500", e.getMessage());
        }
        return Arrays.asList("200", "status changed");
    }

    public List<String> setDeadline(String registry, String subjectId, ExamType exam, String deadline)
    {
        User user = U_DAO.findByRegistry(registry);
        Subject subject = S_DAO.findById(subjectId);
        
        if (user == null) {
            return Arrays.asList("400", "inexistent user");
        }
        if (subject == null) {
            return Arrays.asList("400", "inexistent subject");
        }

        try {
            E_DAO.update(user, subject, exam, "deadline", deadline);
        }
        catch (ParseException e) {
            return Arrays.asList("400", "deadline not convertible to Date object");
        }
        catch (NotEnrolledException e) {
            return Arrays.asList("400", e.getMessage());
        }
        catch (Exception e) {
            return Arrays.asList("500", e.getMessage());
        }
        return Arrays.asList("200", "deadline changed");
    }
}
