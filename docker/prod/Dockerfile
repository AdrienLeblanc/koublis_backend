FROM openjdk:11.0-jre-slim
WORKDIR /app
COPY target/*.jar koublis.jar
ENTRYPOINT ["java","-jar","koublis.jar"]