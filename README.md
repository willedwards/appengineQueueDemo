Appengine Queue + Task + Worker Demo
=============================

I have put this code here to help others get started on the very useful feature of Tasks and Workers connected by Queues

The whole concept is similar to a producer + consumer pattern, connected by a queue. Only the Task = producer, the Worker = Consumer.

This is a nice way of doing threads without the boiler plate thread code. Rather it's kept to http.

To run on the command line:
```
>mvn appengine:devserver
```

Firstly, look at the web.xml, and take note of the servlet mappings for QueueMailTaskServlet and SendConfirmationLLServlet.

Now, take a look at QueueMailTaskServlet

This is the route in, so that when you call it via a HTTP GET, it will create a Task to be POSTed to /mail/low-level
as well as a parameter named message, to be placed on the default queue.

Note, there is no knowledge of the receiving servlet, just the queue and the url (/mail/low-level). This is the decoupling effect of the pattern.

Now, look at the SendConfirmationLLServlet, it will act when POSTed to, and will write out a message.

If this was synchronous, then the last thing being written out to the console would be "doing something else now". But it is not.

Try using POSTMAN, and post to :

```
http://localhost:8080/custom/queueTask?message=hello
```

If this works as it should, you will observe the logs come out in a non sequential (different) order ! And there you have a more responsive design. So the worker could be used for sending emails, or sending an sms via a third party.

