package ut.io.sei.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import io.sei.api.controller.Loginctl;

public class LoginctlTest
{
    // NOTE: The controller does NOT cover test #7 (null user/password fields),
    // that's the endpoint's job!
    private static final Loginctl ctl = new Loginctl();
    public LoginctlTest() {

    }

    @Test
    public void testLoginOk() {
        assertEquals(0, ctl.verifyLogin("admin", "R!c|<r0ll"),
                     "Correct login returned code != 0");
    }

    @Test
    public void testLoginMissingUser() {
        assertEquals(1, ctl.verifyLogin("", "abc"),
                     "Missing user returned code != 1");
    }

    @Test
    public void testLoginMissingPassword() {
        assertEquals(2, ctl.verifyLogin("admin", null),
                     "Missing password returned code != 2");
    }

    @Test
    public void testLoginMissingBothFields() {
        assertEquals(3, ctl.verifyLogin(null, ""),
                     "Missing both fields returned code != 3");
    }

    @Test
    public void testLoginInexistentUser() {
        assertEquals(4, ctl.verifyLogin("inexistent", "nothing here"),
                     "Inexistent user returned code != 4");
    }

    @Test
    public void testLoginWrongPassword() {
        assertEquals(5, ctl.verifyLogin("admin", "wrong"),
                     "Wrong password returned code != 5");
    }
}