# Stufe 1: Build-Phase
FROM gradle:jdk21-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# for all env-variables that we will use in the future:
ARG DB_PASSWORD
ARG DB_URL
ARG DB_USER
RUN gradle build --no-daemon

# Stufe 2: Ausführungsphase
FROM eclipse-temurin:21-jdk-jammy
COPY --from=build /home/gradle/src/build/libs/noteblock-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]