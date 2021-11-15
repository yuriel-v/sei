package it.io.sei.api.routes;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.google.gson.Gson;

public abstract class EndpointTest
{
    protected static final Gson GSON = new Gson();

    protected static final String port = "9080";
    protected static final String url = String.format("http://localhost:%s/", port);
    protected final Client client = ClientBuilder.newClient();
    protected final WebTarget target = client.target(url + "login");
}
