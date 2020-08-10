## Jetty API using Project Loom a.k.a "Letty"

This is a sample API that uses Jetty to expose an endpoint showcasing how to use Project Loom's virtual threads
in a real scenario

For testing purposes, the application can be started in two different modes: `loom` and `standard`. Just set the
`SERVER_MODE` environment variable to one of these values!

You can also generate a docker image from this application. 
Just execute `make docker appVersion={version-you-want-to-generate}`