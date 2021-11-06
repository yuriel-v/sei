package it.io.sei.api.routes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Properties;

import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

public class IndexTest 
{
    private static final Gson GSON = new Gson();

    public IndexTest() {

    }

    @Test
    public void testIndexConnectivity()
    {
        // TODO: Figure out how the hell to get these properties dynamically like that
        // @SuppressWarnings("unused")
        // String context = System.getProperty("context.root");  // just to illustrate how to get the root, it will always be '/' in our project
        // String port = System.getProperty("http.port");

        // For now this will do
        String port = "9080";
        String url = String.format("http://localhost:%s/", port);

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url + "index");

        Response response = target.request().get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(),
                     "Incorect response code from " + url);
        
        String json = response.readEntity(String.class);
        Properties sysProps = GSON.fromJson(json, Properties.class);

        assertEquals(System.getProperty("os.name"), sysProps.getProperty("os.name"),
                     "System property for local and remote JVM should match");
        
        response.close();
        
    }
}
