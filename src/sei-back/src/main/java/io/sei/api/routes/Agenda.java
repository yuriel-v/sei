package io.sei.api.routes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.sei.api.controller.Agendactl;
import io.sei.db.dao.UserDao;
import io.sei.db.model.User;

@Path("/agenda")
public class Agenda 
{
    private static final Agendactl agendactl = new Agendactl();
    private static final Gson GSON = new Gson();
    private static final UserDao U_DAO = new UserDao();

    private boolean checkPostBody(HashMap<String, Object> body)
    {
        String[] elements = {"user", "action", "data"};
        for (String element : elements)
        {
            if (!body.containsKey(element)) {
                return false;
            }
        }
        return true;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAgenda(@QueryParam("m") String registry)
    {
        User user = U_DAO.findByRegistry(registry);
        if (user == null)
        {
            return Response
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .entity(GSON.toJson(Map.of("status", "user not found")))
                .build();
        }
        else
        {
            return Response
                .ok(GSON.toJson(Map.of(
                    "status", "ok",
                    "registry", user.getRegistry(),
                    "enrollments", user.getEnrolledSubjects(),
                    "name", user.getName(),
                    "email", user.getEmail()
                ))).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyAgenda(HashMap<String, Object> body)
    {
        Response incomplete = Response.status(Response.Status.BAD_REQUEST)
                                      .entity(GSON.toJson(Map.of("status", "request body incomplete")))
                                      .build();
        if (!checkPostBody(body)) {
            return incomplete;
        }

        String registry = (String) body.get("user");
        String action = (String) body.get("action");
        HashMap<String, String> data = GSON.fromJson(
            GSON.toJson(body.get("data")), new TypeToken<HashMap<String, String>>(){}.getType()
        );

        List<String> result = null;

        for (String act : new String[] {"enroll", "lock", "grade", "status", "deadline"})
        {
            if (action.compareToIgnoreCase(act) == 0)
            {
                if (!checkData(data, act)) {
                    return incomplete;
                }
                result = agendactl.call(act, registry, data);
                break;
            }
        }

        if (result == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                           .entity(GSON.toJson(Map.of("status", String.format("unknown action '%s'", action))))
                           .build();
        }
        else {
            return Response.status(Integer.parseInt(result.get(0)))
                           .entity(GSON.toJson(Map.of("status", result.get(1))))
                           .build();
        }
    }

    private boolean checkData(HashMap<String, String> data, String action)
    {
        if (!data.containsKey("subjectId")) {
            return false;
        }

        if (action.compareTo("enroll") == 0 || action.compareTo("lock") == 0) {
            return true;  // will never get here if it fails the above check, which is all that's needed for these
        }
        else if (action.compareTo("grade") == 0) {
            return data.containsKey("exam") && data.containsKey("grade");
        }
        else if (action.compareTo("status") == 0) {
            return data.containsKey("exam") && data.containsKey("status");
        }
        else if (action.compareTo("deadline") == 0) {
            return data.containsKey("exam") && data.containsKey("deadline");
        }
        else {
            return false;
        }
    }
}

