Dockerizing a Scala Application
===============================

This is a simple example of how to create Docker image for a Scala
application.

To build the Docker image, run following commands in sbt
```
> compile
> docker:publishLocal
```

To run the docker image, run following command in terminal
```
$ docker run -p 8080:8080 example/example-service:0.1.0
```

Open [http://localhost:8080/hello](http://localhost:8080/hello) in browser
and you'll see a hello page.
