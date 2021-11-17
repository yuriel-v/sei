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
            SubjectDao sdao = new SubjectDao();

            User adm = new User(
                "admin", "admin@sei.io", "9999999999", BCrypt.hashpw("R!c|<r0ll", BCrypt.gensalt())
            );
            adm.getAllEnrolledSubjects()
                .add(new Enrollment(sdao.findById("DAW")));

            User lbval = new User("Leonardo Braga Valim", "2019101557@aluno.unicarioca.edu.br", "2019101557", BCrypt.hashpw("lbval", BCrypt.gensalt()));
            for (String subjectID : new String[] {"DAW", "MDC", "EMP", "ARD", "QSW", "PEC", "MNC"})
            {
                Subject subject = sdao.findById(subjectID);
                lbval.getAllEnrolledSubjects().add(new Enrollment(subject));
            }

            User gfids = new User("Gabriel Fidelis Souza", "2019101478@aluno.unicarioca.edu.br", "2019101478", BCrypt.hashpw("gfids", BCrypt.gensalt()));
            for (String subjectID : new String[] {"DAW", "IAR", "ARD", "PEC", "MNC"})
            {
                Subject subject = sdao.findById(subjectID);
                gfids.getAllEnrolledSubjects().add(new Enrollment(subject));
            }

            UserDao.users.put(adm.getRegistry(), adm);
            UserDao.users.put(lbval.getRegistry(), lbval);
            UserDao.users.put(gfids.getRegistry(), gfids);
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
