package io.sei.backend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "IndexServlet", urlPatterns = {""})
public class IndexServlet extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("{\"response\": \"Index resource servlet!\"}");
        out.flush();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("{\"response\": \"Index resource servlet!\"}");
        out.flush();
    }
}
