FROM eclipse-temurin:17-jre

WORKDIR /opt

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]