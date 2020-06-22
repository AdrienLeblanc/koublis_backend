FROM openjdk:11.0-jre-slim
WORKDIR /app
COPY target/*.jar cinecity.jar
ENTRYPOINT ["java","-jar","cinecity.jar"]