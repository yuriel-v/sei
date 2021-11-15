package io.sei.api.routes;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import io.sei.db.dao.UserDao;
import io.sei.db.model.User;

@Path("/agenda")
public class Agenda 
{
    // private static final Agendactl = new Agendactl();
    private static final Gson GSON = new Gson();
    private static final UserDao U_DAO = new UserDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAgenda(@PathParam("m") String registry)
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
                    "enrollments", user.getRegisteredSubjects()
                ))).build();
        }
    }
}

