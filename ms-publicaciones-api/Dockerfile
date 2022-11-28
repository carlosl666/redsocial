FROM openjdk:11-jdk-slim as builder

WORKDIR /app/ms-publicaciones-api

COPY ./pom.xml /app
COPY ./ms-publicaciones-api/.mvn ./.mvn
COPY ./ms-publicaciones-api/mvnw .
COPY ./ms-publicaciones-api/pom.xml .

#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline

COPY ./ms-publicaciones-api/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:11-jdk-slim

WORKDIR /app
COPY --from=builder /app/ms-publicaciones-api/target/ms-publicaciones-api-0.0.1-SNAPSHOT.jar .
EXPOSE 8003

ENTRYPOINT ["java", "-jar", "ms-publicaciones-api-0.0.1-SNAPSHOT.jar"]