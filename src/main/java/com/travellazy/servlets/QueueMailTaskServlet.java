package com.travellazy.servlets;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class QueueMailTaskServlet extends HttpServlet {
    private static final Logger log =  Logger.getLogger(QueueMailTaskServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        TaskOptions task = TaskOptions.Builder.withUrl("/mail/low-level")
                .param("recipient","will.edwards@me.com")
                .param("message","What is faster ?");


        Queue queue = QueueFactory.getDefaultQueue();
        log.info("adding task to default queue");
        queue.add(task);
        log.info("doing something else now");

        response.sendRedirect("/");
    }
}
