package io.sei.db.dao;

import javax.persistence.EntityManager;

import io.sei.api.model.Materia;

public class MatDaoImp implements GenericDaoMat{
    private static String[] login = {"admin","R!c|<r0ll"};
    private EntityManager em;

    public MatDaoImp(EntityManager em) {
    this.em = em;
    }
    
    @Override
    public void add(Materia materia) {
    em.persist(materia);
    }
    
    @Override
    public void delete(int id) {
    em.remove(findById(id));
    }
    
    @Override
    public void update(Materia materia) {
    em.merge(materia);
    }

    @Override
    public Materia findById(int id) {
    return null;
    }

    
}
