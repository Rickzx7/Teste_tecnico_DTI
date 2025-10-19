FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y sqlite3 && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY build/libs/teste_tecnico_dti-0.0.1-SNAPSHOT.jar app.jar

RUN mkdir -p /app/data

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
