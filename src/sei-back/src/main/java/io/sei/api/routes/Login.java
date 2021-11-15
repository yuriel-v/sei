package io.sei.api.routes;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import io.sei.api.controller.Loginctl;

@Path("/login")
public class Login
{
    private static final Loginctl loginctl = new Loginctl();
    private static final Gson GSON = new Gson();

    public Login() {

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doLogin(HashMap<String, Object> body) 
    {
        if (!(body.containsKey("user") && body.containsKey("password")))
            return Response
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .entity(GSON.toJson(new HashMap<String, String>(Map.of("status", "incomplete_body"))))
                .build();
        
        final int code = loginctl.verifyLogin(body.get("user").toString(), body.get("password").toString());
        final String[] statuses = new String[]
            {"ok", "missing_user", "missing_password", "missing_fields", "inexistent_user", "wrong_password"};
        final int[] httpStatuses = new int[]
            {200, 400, 400, 400, 401, 401};

        return Response
            .status(httpStatuses[code])
            .entity(GSON.toJson(new HashMap<String, String>(Map.of("status", statuses[code]))))
            .build();
    }
}