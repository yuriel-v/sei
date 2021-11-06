package io.sei.api.controller;

public class Loginctl
{
    public Loginctl() {

    }

    public int verifyLogin(String username, String password)
    {
        // 0: OK
        // 1: Missing user
        // 2: Missing password
        // 3: Missing fields
        // 4: Inexistent user
        // 5: Wrong password

        int status = (username == null || username.isBlank() || username.isEmpty()) ? 1 : 0;
        status += (password == null || password.isEmpty()) ? 2 : 0;

        if (status != 0)
            return status;
        else
        {
            if (username.compareTo("admin") != 0)
                return 4;
            else if (password.compareTo("R!c|<r0ll") != 0)
                return 5;
            else
                return 0;
        }
    }
}
