package io.sei.api.controller;

import org.mindrot.jbcrypt.BCrypt;

import io.sei.db.dao.UserDao;
import io.sei.db.model.User;

public class Loginctl
{
    private static final UserDao U_DAO = new UserDao();
    public Loginctl() {

    }

    public int verifyLogin(String registry, String password)
    {
        // 0: OK
        // 1: Missing user
        // 2: Missing password
        // 3: Missing fields
        // 4: Inexistent user
        // 5: Wrong password

        int status = (registry == null || registry.isBlank() || registry.isEmpty()) ? 1 : 0;
        status += (password == null || password.isEmpty()) ? 2 : 0;

        if (status != 0)
            return status;
        else
        {
            User user = U_DAO.findByRegistry(registry);
            if (user == null) {
                return 4;
            }
            else if (!BCrypt.checkpw(password, user.getPasswordHash())) {
                return 5;
            }
            else
                return 0;
        }
    }
}
