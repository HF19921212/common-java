package com.city.common.servlets;

import com.city.common.http.Request;
import com.city.common.http.Response;
import com.city.common.http.Servlet;

import java.io.UnsupportedEncodingException;

public class MyServlet extends Servlet {

    @Override
    public void doGet(Request request, Response response) {
        try {
            response.write(request.getParameter("name"),200);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request request, Response response) {
        doGet(request,response);
    }

}
