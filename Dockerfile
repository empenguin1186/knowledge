FROM openjdk:11

WORKDIR /app
COPY ./build/libs/knowledge-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java", "-jar", "knowledge-0.0.1-SNAPSHOT.jar"]