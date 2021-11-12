package io.sei.db.dao;

import io.sei.api.model.User;

public interface GenericDaoUser {

    public void add(User user);

    public User findById(int id);

    public void delete(int id);

    public void update(User user);
}
