FROM openjdk:17-alpine
COPY build/libs/*.jar reduse.jar
ENTRYPOINT ["java" , "-jar" , "/reduse.jar"]