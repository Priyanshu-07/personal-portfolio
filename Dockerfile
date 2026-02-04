FROM eclipse-temurin:17-jre-jammy

LABEL maintainer="Priyanshu Verma"

WORKDIR /app

COPY target/personalportfolio-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","app.jar"]
