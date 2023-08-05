# Reduse
### Introduction
Frankly it is just acronym for reactive redis url shortener with bunches of additional word, just made it more brand-like name!

### Note
I do not have Docker, so you may find error in docker-compose or Dockerfile; anyway i just used my god-like imaginary!

You have two api path
* /compact : you send url with PUT and json body then you got json result whom contain the shortened link.
* /extract?key=shortened-url : you got the shortened url from compact path then you can use it as query param and retrieve the original one!
### Reference Documentation

For further reference, please consider the following sections:

* [Validation](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#io.validation)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#web.reactive)
* [Spring Data Reactive Redis](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#data.nosql.redis)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#features.docker-compose)

### Guides

The following guides illustrate how to use some features concretely:

* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Docker Compose support

This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* redis: [`redis:latest`](https://hub.docker.com/_/redis)

Please review the tags of the used images and set them to the same as you're running in production.

