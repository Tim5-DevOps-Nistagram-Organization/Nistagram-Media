FROM maven:3.8.1-jdk-11 AS nistagramMediaMicroserviceTest
ARG STAGE=test
WORKDIR /usr/src/server
COPY . .
