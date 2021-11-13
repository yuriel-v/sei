package io.sei.db.dao;

import java.util.ArrayList;

import io.sei.db.model.User;

public class UserDao implements GenericDaoUser{
        private static ArrayList<User> users = new ArrayList<User>();

        @Override
        public ArrayList<User> getall(User users) {
                return getall(users);
        }


        @Override
        public void add(User user) {
                users.add(user);
                
        }


        @Override
        public void update(User user) {
                users.add(user);      
        }


        @Override
        public ArrayList<User> findByRegistry(String registry) {
                return users;
        }


        @Override
        public void delete(User registry) {
                users.remove(registry);
                
        }

}


