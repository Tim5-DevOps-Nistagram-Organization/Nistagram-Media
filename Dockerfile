FROM maven:3.8.1-jdk-11 AS nistagramMediaMicroserviceTest
ARG STAGE=test
WORKDIR /usr/src/server
COPY . .

FROM maven:3.8.1-jdk-11  AS nistagramMediaMicroserviceBuild
ARG STAGE=dev
WORKDIR /usr/src/server
COPY . .
RUN mvn package -Pdev -DskipTests

FROM openjdk:11.0-jdk as nistagramMediaMicroserviceRuntime
COPY --from=nistagramMediaMicroserviceBuild /usr/src/server/target/*.jar nistagram-media.jar
CMD java -jar nistagram-media.jar


