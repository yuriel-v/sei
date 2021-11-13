package io.sei.db.dao;

import java.util.ArrayList;

import io.sei.db.model.User;

public interface GenericDaoUser {

    public void add(User user);

    public ArrayList<User> findByRegistry(String registry);

    public void delete(User user);

    public void update(User user);

    public ArrayList<User> getall(User user);
}
