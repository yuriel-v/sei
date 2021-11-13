package io.sei.db.dao;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import io.sei.db.model.User;

public class UserDao
{
    private static final HashMap<String, User> users = new HashMap<String, User>();

    public UserDao() { }

    private static void checkNullFields(User user) throws IOException
    {
        for (String field : Arrays.asList(user.getName(), user.getEmail(), user.getRegistry(), user.getPasswordHash()))
        {
            if (field.isBlank() || field == null) {
                throw new IOException("Null or empty fields are not allowed");
            }
        }
    }

    public void add(User user) throws IOException
    {
        if (this.exists(user.getRegistry())) {
            throw new IOException("User already exists in database");
        }
        else
        {
            UserDao.checkNullFields(user);
            UserDao.users.put(user.getRegistry(), user);
        }
    }

    public void add(String name, String email, String registry, String passwordHash) throws IOException
    {
        User usr = new User(name, email, registry, passwordHash);
        this.add(usr);
    }

    public User findByRegistry(String registry) {
        return UserDao.users.get(registry);
    }
    
    private boolean exists(String registry) {
        return UserDao.users.containsKey(registry);
    }

    public void update(User user) throws IOException
    {
        if (!this.exists(user.getRegistry())) {
            throw new IOException("User does not exist in database");
        }

        // needed so as to preserve the user's registries in subjects
        // maybe make a method to change all these at once?
        User curUser = this.findByRegistry(user.getRegistry());
        curUser.setEmail(user.getEmail());
        curUser.setName(user.getName());
        curUser.setPasswordHash(user.getPasswordHash());

        UserDao.users.put(user.getRegistry(), user);
    }

    public void update(String registry, String newName, String newEmail, String newPasswordHash) throws IOException
    {
        User usr = new User(newName, newEmail, registry, newPasswordHash);
        this.update(usr);
    }

    public User delete(String registry) throws IOException
    {
        if (!this.exists(registry)) {
            throw new IOException("User does not exist in database");
        }

        return UserDao.users.remove(registry);
    }
}
