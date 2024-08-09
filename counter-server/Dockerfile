FROM gradle:8.8-jdk17 AS build

WORKDIR /app

COPY build.gradle settings.gradle ./

RUN gradle dependencies --no-daemon

COPY . /app

RUN gradle clean build --no-daemon

FROM openjdk:17.0.2-slim-buster

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/others.jar

EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar", "others.jar"]
