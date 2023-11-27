FROM openjdk:11.0-jre-slim
WORKDIR /app
COPY target/*.jar koublis.jar
ENTRYPOINT ["java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,address=*:8000,server=y,suspend=n", "-jar","koublis.jar"]