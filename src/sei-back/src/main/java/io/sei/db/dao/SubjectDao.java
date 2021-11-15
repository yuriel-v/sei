package io.sei.db.dao;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import io.sei.db.model.Subject;

public class SubjectDao
{
    private static final HashMap<String, Subject> subjects = new HashMap<String, Subject>();

    public SubjectDao() { }

    private static void checkNullFields(Subject subject) throws IOException
    {
        for (String field : Arrays.asList(subject.getId(), subject.getName()))
        {
            if (field.isBlank() || field == null) {
                throw new IOException("Null or empty fields are not allowed");
            }
        }
    }

    public void add(Subject subject) throws IOException
    {
        if (this.exists(subject.getId())) {
            throw new IOException("Subject already exists in database");
        }
        else
        {
            SubjectDao.checkNullFields(subject);
            SubjectDao.subjects.put(subject.getId(), subject);
        }
    }

    public void add(String id, String name) throws IOException
    {
        Subject sbj = new Subject(id, name);
        this.add(sbj);
    }

    public Subject findById(String id) {
        return SubjectDao.subjects.get(id);
    }
    
    private boolean exists(String id) {
        return SubjectDao.subjects.containsKey(id);
    }

    public void update(Subject subject) throws IOException
    {
        if (!this.exists(subject.getId())) {
            throw new IOException("Subject does not exist in database");
        }

        if (!(subject.getName().isBlank() || subject.getName() == null)) {
            SubjectDao.subjects.put(subject.getId(), subject);
        }
        else {
            throw new IOException("Subject must not have a null or blank name");
        }
    }

    public void update(String id, String newName) throws IOException
    {
        Subject sbj = new Subject(id, newName);
        this.update(sbj);
    }

    public Subject delete(String id) throws IOException
    {
        if (!this.exists(id)) {
            throw new IOException("Subject does not exist in database");
        }

        return SubjectDao.subjects.remove(id);
    }
}