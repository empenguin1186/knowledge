FROM openjdk:11

WORKDIR /app
COPY ./knowledge-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "knowledge-0.0.1-SNAPSHOT.jar"]