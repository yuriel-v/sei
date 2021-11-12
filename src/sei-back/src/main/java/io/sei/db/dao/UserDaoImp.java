package io.sei.db.dao;

import javax.persistence.EntityManager;

import io.sei.api.model.User;

public class UserDaoImp implements GenericDaoUser{
        private static String[] login = {"admin","R!c|<r0ll"};
        private EntityManager em;

        public UserDaoImp(EntityManager em) {
        this.em = em;
        }
        
        @Override
        public void add(User user) {
        em.persist(user);
        }
        
        @Override
        public void delete(int id) {
        em.remove(findById(id));
        }
        
        @Override
        public void update(User user) {
        em.merge(user);
        }

        @Override
        public User findById(int id) {
        return null;
        }

}


