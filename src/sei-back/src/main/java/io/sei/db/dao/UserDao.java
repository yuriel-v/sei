package io.sei.db.dao;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import io.sei.db.model.Enrollment;
import io.sei.db.model.Subject;
import io.sei.db.model.User;

public class UserDao
{
    private static final HashMap<String, User> users = new HashMap<String, User>();

    public UserDao()
    {
        if (UserDao.users.isEmpty())
        {
            User adm = new User(
                "admin", "admin@sei.io", "9999999999", BCrypt.hashpw("R!c|<r0ll", BCrypt.gensalt())
            );
            adm.getEnrolledSubjects()
                .add(new Enrollment(new Subject("DAW", "Desenvolvimento de Aplicações Web")));
                // yes, i'm aware this is a duped subject.
                // for all intents and purposes, it'll act the same as the one in the subject DAO.
                // verification is done by checking if subject1.getId() == subject2.getId() so it's safe.
                //
                // terrible practice, but the only other option is using more shenanigans to expose subjectdao's
                // storage and fetching it from there, in which case it might not even exist yet. this is
                // the "least wrong" and sure-fire answer.

            UserDao.users.put("9999999999", adm);
        }
    }

    private static void checkNullFields(User user) throws IOException
    {
        for (String field : Arrays.asList(user.getName(), user.getEmail(), user.getRegistry(), user.getPasswordHash()))
        {
            if (UserDao.isNullField(field)) {
                throw new IOException("Null or empty fields are not allowed");
            }
        }
    }

    private static boolean isNullField(String field) {
        return (field.isBlank() || field == null);
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

        // needed so as to preserve the user's enrollments in subjects
        // maybe make a method to change all these at once?
        User curUser = this.findByRegistry(user.getRegistry());
        if (!UserDao.isNullField(user.getEmail())) {
            curUser.setEmail(user.getEmail());
        }
        if (!UserDao.isNullField(user.getName())) {
            curUser.setName(user.getName());
        }
        if (!UserDao.isNullField(user.getPasswordHash())) {
            curUser.setPasswordHash(user.getPasswordHash());
        }

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
