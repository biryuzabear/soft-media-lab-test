FROM gradle:jdk17 AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle clean build --no-daemon -x test

FROM openjdk:17-jdk-slim

COPY --from=build /home/gradle/src/build/libs/test-0.0.1.jar /app/test-0.0.1.jar

WORKDIR /app
EXPOSE 8080

CMD ["java", "-jar", "test-0.0.1.jar"]
