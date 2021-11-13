package io.sei.db.dao;

import java.util.ArrayList;

import io.sei.db.model.Subject;

public class MatDao implements GenericDaoMat{
    private static ArrayList<Subject> subject = new ArrayList<Subject>();
    
    @Override
    public void add(Subject subject) {
        add(subject);
    }

    @Override
    public ArrayList<Subject> findByName(String name) {
        return subject;
    }

    @Override
    public void delete(Subject name) {
        subject.remove(name);
        
    }

    @Override
    public void update(Subject subject) {
        add(subject);
    }

    
}
