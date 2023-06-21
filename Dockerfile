FROM openjdk:17
LABEL maintainer="rahulbalaji"
ADD target/spring-0.0.1-SNAPSHOT.jar spring-docker-fable.jar
ENTRYPOINT ["java", "-jar", "spring-docker-fable.jar"]