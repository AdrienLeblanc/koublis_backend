FROM openjdk:11.0-jre-slim
WORKDIR /app
COPY target/*.jar cinecity.jar
ENTRYPOINT ["java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,address=*:8000,server=y,suspend=n", "-jar","cinecity.jar"]