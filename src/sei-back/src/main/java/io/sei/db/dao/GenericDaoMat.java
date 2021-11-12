package io.sei.db.dao;

import io.sei.api.model.Materia;

public interface GenericDaoMat {
    
    public void add(Materia materia);

    public Materia findById(int id);

    public void delete(int id);

    public void update(Materia materia);
}

