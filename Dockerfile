FROM eclipse-temurin:17-jre-jammy

LABEL maintainer="Priyanshu Verma"

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","app.jar"]
