Appengine Queue + Task + Worker Demo
=============================

I have put this code here for two reasons:
- 1) to help others get started on the very useful feature of Tasks and Workers connected by Queues
- 2) slightly suprised at the lack of complete code java examples for appengine queues.

The whole concept is similar to a producer + consumer pattern, connected by a queue. Only the Task = producer, the Worker = Consumer.

This is a nice way of doing threads without the boiler plate thread code. Rather it's kept to http.

To run on the command line:


```
ensure you are running java 7 !!!

>mvn appengine:devserver
```

Firstly, look at the web.xml, and take note of the servlet mappings for QueueMailTaskServlet and SendConfirmationLLServlet.

Now, take a look at  [QueueMailTaskServlet](./src/main/java/com/travellazy/servlets/QueueMailTaskServlet.java)

This is the route in, so that when you call it via a **HTTP GET**, it will create a Task to be **POST**ed to /mail/low-level
as well as a parameter named *message*, to be placed on the default queue.

Note, there is no knowledge of the receiving servlet, just the queue and the url (/mail/low-level). This is the glue that connects the call to the worker. They are both nicely decoupled from each other.

Now, look at the [SendConfirmationLLServlet](./src/main/java/com/travellazy/servlets/SendConfirmationLLServlet.java), it will act when POSTed to, and will write out a message.

If this was synchronous, then the last thing being written out to the console would be "doing something else now". But it is not.

Try using POSTMAN, and post to :

```
http://localhost:8080/custom/queueTask?message=hello
```

You will observe the logs come out in a non sequential (different) order ! And there you have a more responsive design. So the worker could be used for sending emails, or sending an sms via a third party.



