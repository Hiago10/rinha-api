FROM openjdk:17-jdk

WORKDIR /app

ARG JAR_FILE 

COPY target/${JAR_FILE} /app/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]