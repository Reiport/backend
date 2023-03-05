FROM gradle as gradle

WORKDIR /usr/src/app
COPY . /usr/src/app/

RUN gradle build --scan

# Java 17
FROM eclipse-temurin:17-jdk-alpine
ARG EXECUTABLE=*.jar

WORKDIR /opt/app

COPY --from=gradle /usr/src/app/build/libs/${EXECUTABLE} /opt/app/app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]