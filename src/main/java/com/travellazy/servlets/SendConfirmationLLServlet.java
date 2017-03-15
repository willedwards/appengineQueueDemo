package com.travellazy.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class SendConfirmationLLServlet extends HttpServlet {
    private static final Logger log =  Logger.getLogger(SendConfirmationLLServlet.class.getName());

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String message = request.getParameter("message");

        log.info("sending email...");
        StringBuilder sb = new StringBuilder("the email service has been triggered");
        sb.append("with message = " + message);
        sb.toString();
        log.info("sent email");
    }
}
