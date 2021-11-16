package it.io.sei.api.routes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

public class LoginTest extends EndpointTest
{
    // 7 test cases:
    // - #1: valid login - returns 200 OK, login status 'ok'
    // - #2: inexistent user - returns 404 Not Found, login status 'inexistent_user'
    // - #3: missing user field (empty string) - returns 400 Bad Request, login status 'missing_user'
    // - #4: missing password field (empty string) - returns 400 Bad Request, login status 'missing_password'
    // - #5: missing both fields (empty strings) - returns 400 Bad Request, login status 'missing_fields'
    // - #6: wrong password - returns 401 Unauthorized, login status 'wrong_password'
    // - #7: missing 'username' or 'password' (or both) from JSON keys - returns 400 Bad Request, login status 'incomplete_body'
    //   - Test #7 also covers the case where either 'username' or 'password' or both have a null value.


    public LoginTest() {
        
    }

    @Test
    public void testLoginOk()
    {
        HashMap<String, String> reqBody = new HashMap<String, String>(Map.of(
            "user", "admin",
            "password", "R!c|<r0ll"
        ));

        Response response = target.request().post(Entity.json(GSON.toJson(reqBody)));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(),
            "Incorrect return code"
        );

        HashMap<String, Object> responseMap = GSON.fromJson(
            response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
        );
        assertTrue(responseMap.containsKey("status"),
                    "Missing 'status' key from response");
        assertEquals("ok", responseMap.get("status"),
            "Wrong login status message"
        );

        response.close();
    }

    @Test
    public void testLoginInexistentUser()
    {
        HashMap<String, String> reqBody = new HashMap<String, String>(Map.of(
            "user", "inexistent",
            "password", "nothing here"
        ));

        Response response = target.request().post(Entity.json(GSON.toJson(reqBody)));

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus(),
            "Incorrect return code"
        );

        HashMap<String, Object> responseMap = GSON.fromJson(
            response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
        );
        assertTrue(responseMap.containsKey("status"),
                    "Missing 'status' key from response");
        assertEquals("inexistent_user", responseMap.get("status"),
            "Wrong login status message"
        );

        response.close();
    }

    @Test
    public void testLoginMissingUser()
    {
        HashMap<String, String> reqBody = new HashMap<String, String>(Map.of(
            "user", "",
            "password", "nothing here"
        ));

        Response response = target.request().post(Entity.json(GSON.toJson(reqBody)));

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus(),
            "Incorrect return code"
        );

        HashMap<String, Object> responseMap = GSON.fromJson(
            response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
        );
        assertTrue(responseMap.containsKey("status"),
                    "Missing 'status' key from response");
        assertEquals("missing_user", responseMap.get("status"),
            "Wrong login status message"
        );

        response.close();
    }

    @Test
    public void testLoginMissingPassword()
    {
        HashMap<String, String> reqBody = new HashMap<String, String>(Map.of(
            "user", "inexistent",
            "password", ""
        ));

        Response response = target.request().post(Entity.json(GSON.toJson(reqBody)));

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus(),
            "Incorrect return code"
        );

        HashMap<String, Object> responseMap = GSON.fromJson(
            response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
        );
        assertTrue(responseMap.containsKey("status"),
                    "Missing 'status' key from response");
        assertEquals("missing_password", responseMap.get("status"),
            "Wrong login status message"
        );

        response.close();
    }

    @Test
    public void testLoginMissingFields()
    {
        HashMap<String, String> reqBody = new HashMap<String, String>(Map.of(
            "user", "",
            "password", ""
        ));

        Response response = target.request().post(Entity.json(GSON.toJson(reqBody)));

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus(),
            "Incorrect return code"
        );

        HashMap<String, Object> responseMap = GSON.fromJson(
            response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
        );
        assertTrue(responseMap.containsKey("status"),
                    "Missing 'status' key from response");
        assertEquals("missing_fields", responseMap.get("status"),
            "Wrong login status message"
        );

        response.close();
    }

    @Test
    public void testLoginWrongPassword()
    {
        HashMap<String, String> reqBody = new HashMap<String, String>(Map.of(
            "user", "admin",
            "password", "wrong"
        ));

        Response response = target.request().post(Entity.json(GSON.toJson(reqBody)));

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus(),
            "Incorrect return code"
        );

        HashMap<String, Object> responseMap = GSON.fromJson(
            response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
        );
        assertTrue(responseMap.containsKey("status"),
                    "Missing 'status' key from response");
        assertEquals("wrong_password", responseMap.get("status"),
            "Wrong login status message"
        );

        response.close();
    }

    @Test
    public void testLoginNullUser()
    {
        HashMap<String, String> reqBody = new HashMap<String, String>(Map.of(
            // user could be null here too, same results - proven on null password below
            "password", "nothing here"
        ));

        Response response = target.request().post(Entity.json(GSON.toJson(reqBody)));

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus(),
            "Incorrect return code"
        );

        HashMap<String, Object> responseMap = GSON.fromJson(
            response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
        );
        assertTrue(responseMap.containsKey("status"),
                    "Missing 'status' key from response");
        assertEquals("incomplete_body", responseMap.get("status"),
            "Wrong login status message"
        );

        response.close();
    }

    @Test
    public void testLoginNullPassword()
    {
        HashMap<String, String> reqBody = new HashMap<String, String>();
            reqBody.put("user", "admin");
            reqBody.put("password", null);  // password could be empty too, same result - proven on null user above

        Response response = target.request().post(Entity.json(GSON.toJson(reqBody)));

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus(),
            "Incorrect return code"
        );

        HashMap<String, Object> responseMap = GSON.fromJson(
            response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
        );
        assertTrue(responseMap.containsKey("status"),
                    "Missing 'status' key from response");
        assertEquals("incomplete_body", responseMap.get("status"),
            "Wrong login status message"
        );

        response.close();
    }

    @Test
    public void testLoginIncompleteBody()
    {
        HashMap<String, String> reqBody = new HashMap<String, String>();
            // missing user, null password
            reqBody.put("password", null);

        Response response = target.request().post(Entity.json(GSON.toJson(reqBody)));

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus(),
            "Incorrect return code"
        );

        HashMap<String, Object> responseMap = GSON.fromJson(
            response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
        );
        assertTrue(responseMap.containsKey("status"),
                    "Missing 'status' key from response");
        assertEquals("incomplete_body", responseMap.get("status"),
            "Wrong login status message"
        );

        response.close();
    }
    

    // big ass smoke test
    // @Test
    // public void testDoLogin()
    // {
    //     String port = "9080";
    //     String url = String.format("http://localhost:%s/", port);

    //     Client client = ClientBuilder.newClient();
    //     WebTarget target = client.target(url + "login");

    //     // these cover tests 1-6, see below
    //     ArrayList<String> users = new ArrayList<String>(
    //         Arrays.asList("admin", "inexistent", "", "admin", "", "admin")
    //     );
    //     ArrayList<String> passwords = new ArrayList<String>(
    //         Arrays.asList("R!c|<r0ll", "nothing here", "abc", "", "", "wrong")
    //     );
    //     ArrayList<Integer> statuses = new ArrayList<Integer>(Arrays.asList(
    //         Response.Status.OK.getStatusCode(),
    //         Response.Status.NOT_FOUND.getStatusCode(),
    //         Response.Status.BAD_REQUEST.getStatusCode(),
    //         Response.Status.BAD_REQUEST.getStatusCode(),
    //         Response.Status.BAD_REQUEST.getStatusCode(),
    //         Response.Status.UNAUTHORIZED.getStatusCode()
    //     ));
    //     ArrayList<String> loginStatuses = new ArrayList<String>(Arrays.asList(
    //         "ok", "inexistent_user", "missing_user", "missing_password", "missing_fields", "wrong_password"
    //     ));

    //     // test 1-6
    //     for (int i = 0; i < 6; ++i)
    //     {
    //         HashMap<String, String> loginInfo = new HashMap<String, String>();
    //             loginInfo.put("user", users.get(i));
    //             loginInfo.put("password", passwords.get(i));
    //         Response response = target.request().post(Entity.json(GSON.toJson(loginInfo)));

    //         assertEquals(statuses.get(i), response.getStatus(),
    //             String.format("Incorrect return code", response.getStatus(), statuses.get(i))
    //         );

    //         HashMap<String, Object> responseMap = GSON.fromJson(
    //             response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
    //         );
    //         assertTrue(responseMap.containsKey("status"),
    //                    "Missing 'status' key from response");
    //         assertEquals(loginStatuses.get(i), responseMap.get("status"), String.format(
    //             "Inexistent or wrong login status message (user %s)", users.get(i)
    //         ));

    //         response.close();
    //     }

    //     // test 7
    //     ArrayList<HashMap<String, String>> incompleteBodies = new ArrayList<HashMap<String, String>>();
    //         incompleteBodies.add(new HashMap<String, String>(Map.of("user", "admin")));   // missing password field
    //         incompleteBodies.add(new HashMap<String, String>(Map.of("password", "abc"))); // missing user field
    //         incompleteBodies.add(new HashMap<String, String>());                          // missing both fields

    //     for (HashMap<String, String> reqBody : incompleteBodies)
    //     {
    //         Response response = target.request().post(Entity.json(GSON.toJson(reqBody)));
    //         assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus(),
    //                      "Malformed request did not return HTTP 400");
            
    //         HashMap<String, Object> responseMap = GSON.fromJson(
    //             response.readEntity(String.class), new TypeToken<HashMap<String, Object>>(){}.getType()
    //         );
    //         assertTrue(responseMap.containsKey("status"),
    //                    "Missing 'status' key from response");
    //         assertEquals("incomplete_body", responseMap.get("status"),
    //                      "Response did not return status 'incomplete_body' on 'status' key");
            
    //         response.close();
    //     }
    // }
}
