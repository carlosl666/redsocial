ARG MS_NAME=ms-usuarios-api

FROM openjdk:11-jdk-slim as builder

ARG MS_NAME

WORKDIR /app/$MS_NAME

COPY ./pom.xml /app
COPY ./$MS_NAME/.mvn ./.mvn
COPY ./$MS_NAME/mvnw .
COPY ./$MS_NAME/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline

COPY ./$MS_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:11-jdk-slim

WORKDIR /app
ARG MS_NAME
ARG NAME_FOLDER=/app/$MS_NAME/target
COPY --from=builder $NAME_FOLDER/ms-usuarios-api-0.0.1-SNAPSHOT.jar .
ARG PORT_API=8002
ENV PORT $PORT_API
EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "ms-usuarios-api-0.0.1-SNAPSHOT.jar"]