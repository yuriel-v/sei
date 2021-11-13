package io.sei.db.dao;

import java.util.ArrayList;

import io.sei.db.model.Subject;

public interface GenericDaoMat {
    
    public void add(Subject subject);

    public ArrayList<Subject> findByName(String name);

    public void delete(Subject subject);

    public void update(Subject subject);
}

