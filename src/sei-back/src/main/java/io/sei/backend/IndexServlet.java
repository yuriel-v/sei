package io.sei.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet(name = "IndexServlet", urlPatterns = {""})
public class IndexServlet extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // more "complete" version
        // showcases hashmap and gson's toJson() conversion
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> jsonResponse = new HashMap<String, String>();
        jsonResponse.put("response", "Index resource servlet!");
        new Gson().toJson(jsonResponse, out);
        out.flush();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // simpler version
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("{\"response\": \"Index resource servlet!\"}");
        out.flush();
    }
}
